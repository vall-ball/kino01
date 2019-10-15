package ru.vallball.kino01.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.vallball.kino01.model.DayList;
import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Session;
import ru.vallball.kino01.service.FilmService;
import ru.vallball.kino01.service.SessionService;

@Controller
@RequestMapping(value = { "/sessions" })
public class SessionController {

	@Autowired
	SessionService sessionService;

	@Autowired
	FilmService filmService;

	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

	@ModelAttribute("sess")
	public Session newSession(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new Session();
		} else
			return sessionService.findSessionById(id);
	}

	@ModelAttribute("dayList")
	public DayList dayList() {
		return new DayList(sessionService);
	}

	@ModelAttribute("listTime")
	public List<LocalTime> listTime() {
		List<LocalTime> list = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			list.add(LocalTime.of(i, 0));
		}
		return list;
	}

	@GetMapping
	public String sessions() {
		return "sessions";
	}

	@GetMapping("/addSession/{id}")
	public String addSession(@PathVariable("id") String date, HttpServletRequest req, Model model) {
		LocalDate d = LocalDate.parse(date);
		List<Film> films = filmService.list();
		model.addAttribute("films", films);
		model.addAttribute("date", d);
		req.getSession().setAttribute("date", d);
		logger.info("Переходим к форме добавления нового сеанса");
		return "addSession";
	}

	@PostMapping("/addSession")
	public String createSession(@ModelAttribute("sess") @Valid Session session, BindingResult result, HttpServletRequest req,
			@RequestParam("time") String time, Model model) {
		session.setDate((LocalDate) req.getSession().getAttribute("date"));
		if (result.hasErrors()) {
			logger.info("Есть ошибка " + session + " " + result.getErrorCount() + " " + result.getAllErrors().get(0));
			model.addAttribute("date", req.getSession().getAttribute("date"));
			List<Film> films = filmService.list();
			model.addAttribute("films", films);
						return "addSession";
		} else {
			logger.info("Ошибки нет ");
			//session.setDate((LocalDate) req.getSession().getAttribute("date"));
			LocalTime t = LocalTime.parse(time);
			session.setTime(t);
			sessionService.save(session);
			return "redirect:/sessions";
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		sessionService.delete(id);
		return "redirect:/sessions";
	}

	@GetMapping("/update/{id}")
	public String change(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
		List<Film> films = filmService.list();
		model.addAttribute("films", films);
		Session session = sessionService.findSessionById(id);
		model.addAttribute("sess", session);
		logger.info("Сессия в гет " + session);
		logger.info("Сессия в модели " + model.asMap().get("sess"));
		return "changeSession";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("sess") @Valid Session session, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Film> films = filmService.list();
			model.addAttribute("films", films);
			return "changeSession";
		} else {
			logger.info("Сессия в гет " + session);
			sessionService.save(session);
			return "redirect:/sessions";
		}
	}

}
