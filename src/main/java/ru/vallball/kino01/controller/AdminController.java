package ru.vallball.kino01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/adminPage" })
public class AdminController {

	@GetMapping
	public String adminPage() {
		return "adminPage";
	}
}
