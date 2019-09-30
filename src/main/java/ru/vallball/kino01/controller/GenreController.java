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

import ru.vallball.kino01.model.Genre;
import ru.vallball.kino01.service.GenreService;


@Controller
@RequestMapping(value = { "/genres" })
public class GenreController {
	

	@Autowired
	GenreService genreService;

	@ModelAttribute("genre")
	public Genre newGenre(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new Genre();
		} else
			return genreService.findGenreById(id);
	}

	@GetMapping
	public String genres(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
		Page<Genre> page = genreService.findAll(pageable);
		model.addAttribute("page", page);
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "genres";
	}
	
	@GetMapping("/addGenre")
	public String addGenre() {
		return "addGenre";
	}

	@PostMapping("/addGenre")
	public String newGenre(@ModelAttribute @Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			return "addGenre";
		} else {
			genreService.save(genre);
			return "redirect:/genres";
		}
	}
	
	@GetMapping("/deleteGenre/{id}")
	public String delete(@PathVariable Long id, Model model) {
		genreService.delete(id);
		return "redirect:/genres";
	}
	
	@GetMapping("/changeGenre/{id}")
	public String change(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
		Genre genre = genreService.findGenreById(id);
		request.getSession().setAttribute("genre", genre);
		model.addAttribute("genre", genre);
		return "changeGenre";
	}

	@PostMapping("/changeGenre")
	public String update(@ModelAttribute @Valid Genre genre, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "changeGenre";
		} else {

			genreService.save(genre);

		}
		return "redirect:/genres";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request, Model model) {
		model.addAttribute("error", ex);
		return "error";
	}

}
