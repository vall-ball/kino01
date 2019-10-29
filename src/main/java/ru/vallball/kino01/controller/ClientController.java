package ru.vallball.kino01.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Place;
import ru.vallball.kino01.model.Row;
import ru.vallball.kino01.model.Session;
import ru.vallball.kino01.model.Status;
import ru.vallball.kino01.model.User;
import ru.vallball.kino01.service.FilmService;
import ru.vallball.kino01.service.PlaceService;
import ru.vallball.kino01.service.SessionService;
import ru.vallball.kino01.service.UserService;

@Controller
@RequestMapping(value = { "/forClient" })
public class ClientController {

	@Autowired
	UserService userService;

	@Autowired
	FilmService filmService;

	@Autowired
	SessionService sessionService;

	@Autowired
	PlaceService placeService;

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@GetMapping
	public String clientPage(Model model, HttpServletRequest req,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		User user = (User) userService.loadUserByUsername(req.getRemoteUser());
		model.addAttribute("user", user);
		Page<Film> page = filmService.findAll(pageable);
		model.addAttribute("page", page);
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "forClient";
	}

	@GetMapping("/sessionOfFilm/{id}")
	public String sessionOfFilm(@PathVariable("id") Long id, HttpServletRequest req, Model model,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		Film film = filmService.findFilmById(id);
		Page<Session> page = sessionService.findAllByFilm(pageable, film);
		model.addAttribute("page", page);
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("film", film);
		User user = (User) userService.loadUserByUsername(req.getRemoteUser());
		model.addAttribute("user", user);
		return "sessionsOfFilm";
	}

	@GetMapping("/choosePlace/{id}")
	public String chooseSession(@PathVariable("id") Long id, HttpServletRequest req, Model model) {
		Session sess = sessionService.findSessionById(id);
		User user = (User) userService.loadUserByUsername(req.getRemoteUser());
		model.addAttribute("sess", sess);
		model.addAttribute("user", user);
		List<Place> places = placeService.findAllBySession(sess);
		List<Row> rows = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			rows.add(new Row(i));
		}
		for (Row row : rows) {
			for (Place place : places) {
				if (place.getLine() == row.getRow()) {
					row.getPlaces().add(place);
				}

			}
		}
		model.addAttribute("rows", rows);
		return "choosePlace";
	}
	
	@PostMapping("/choosePlace")
	public String choosePlaces(@RequestParam("places") Long[] places_id, Model model, HttpServletRequest req) {
		List<Place> places = new ArrayList<>();
		for (int i = 0; i < places_id.length; i++) {
			places.add(placeService.findPlaceById(places_id[i]));
			logger.info("Было выбрано " + placeService.findPlaceById(places_id[i]));
		}
		double sum = 0;
		for (Place place : places) {
			sum = sum + place.getPrice();
		}
		model.addAttribute("places", places);
		model.addAttribute("sum", sum);
		req.getSession().setAttribute("places", places);
		req.getSession().setAttribute("places", places);
		return "yourChoice";
	}
	
	@GetMapping("/saveOrder")
	public String save(HttpServletRequest req) {
		User user = (User) userService.loadUserByUsername(req.getRemoteUser());
		@SuppressWarnings("unchecked")
		List<Place> places = (List<Place>) req.getSession().getAttribute("places");
		for (Place place : places) {
			place.setUser(user);
			place.setStatus(Status.BUSY);
			placeService.update(place);
		}
		return "finalPage";
	}

}
