package ru.vallball.kino01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Session;
import ru.vallball.kino01.service.SessionService;

@Controller
@RequestMapping(value = { "/sessions" })
public class SessionController {

	@Autowired
	SessionService sessionService;

	@ModelAttribute("session")
	public Session newSession(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new Session();
		} else
			return sessionService.findSessionById(id);
	}
	
	

}
