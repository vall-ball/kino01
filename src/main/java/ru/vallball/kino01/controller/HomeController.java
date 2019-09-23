package ru.vallball.kino01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/" })
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/403")
	public String error() {
	   return "403";
	  }
}
