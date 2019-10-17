package ru.vallball.kino01.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.vallball.kino01.model.User;
import ru.vallball.kino01.service.UserService;

@Controller
@RequestMapping(value = { "/forClient" })
public class ClientController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public String clientPage(Model model, HttpServletRequest req) {
		User user = (User) userService.loadUserByUsername(req.getRemoteUser());
		model.addAttribute("user", user);
		return "forClient";
	}

}
