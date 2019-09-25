package ru.vallball.kino01.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@ModelAttribute("genre")
	public Genre newGenre(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new Genre();
		} else
			return genreService.findGenreById(id);
	}

	@GetMapping("/addGenre")
	public String addGenre() {
		return "addGenre";
	}

	@PostMapping("/addGenre")
	public String newGenre(@ModelAttribute @Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			return "addUser";
		} else {
			genreService.save(genre);
			return "redirect:/adminPage";
		}
	}

}
