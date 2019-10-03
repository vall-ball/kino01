package ru.vallball.kino01.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Genre;
import ru.vallball.kino01.model.User;
import ru.vallball.kino01.service.FilmService;
import ru.vallball.kino01.service.GenreService;

@Controller
@RequestMapping(value = { "/films" })
public class FilmController {

	@Autowired
	FilmService filmService;

	@Autowired
	GenreService genreService;

	@ModelAttribute("film")
	public Film newFilm(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new Film();
		} else
			return filmService.findFilmById(id);
	}

	@GetMapping
	public String films(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
		Page<Film> page = filmService.findAll(pageable);
		model.addAttribute("page", page);
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "films";
	}

	@GetMapping("/addFilm")
	public String add(Model model) {
		List<Genre> genres = genreService.list();
		model.addAttribute("genres", genres);

		return "addFilm";
	}

	@PostMapping("/addFilm")
	public String newFilm(@ModelAttribute @Valid Film film, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Genre> genres = genreService.list();
			model.addAttribute("genres", genres);
			return "addFilm";
		} else {
			filmService.save(film);
			return "redirect:/films";
		}
	}

	@GetMapping("/deleteFilm/{id}")
	public String delete(@PathVariable Long id, Model model) {
		filmService.delete(id);
		return "redirect:/films";
	}

	@GetMapping("/changeFilm/{id}")
	public String change(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
		List<Genre> allGenres = genreService.list();
		model.addAttribute("allGenres", allGenres);
		Film film = filmService.findFilmById(id);
		//request.getSession().setAttribute("film", film);
		model.addAttribute("film", film);
		boolean flag = true;
		model.addAttribute("flag", flag);
		return "changeFilm";
	}

	@PostMapping("/changeFilm")
	public String update(@ModelAttribute @Valid Film film, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "changeFilm";
		} else {

			filmService.save(film);
		}
		return "redirect:/films";
	}

	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request, Model model) {
		model.addAttribute("error", ex);
		return "error";
	}

}
