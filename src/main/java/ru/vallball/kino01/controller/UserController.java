package ru.vallball.kino01.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.vallball.kino01.model.User;
import ru.vallball.kino01.service.UserService;

@Controller
@RequestMapping(value = { "/users" })
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ModelAttribute("user")
	public User newUser(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new User();
		} else
			return userService.findUserById(id);
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/addUser")
	public String processRegistration(@ModelAttribute @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "registration";
		} else {
			userService.save(user.toUser(passwordEncoder));
			return "redirect:/";
		}
	}

	@GetMapping("/changeProfile")
	public String changeProfile(HttpServletRequest request, Model model) {
		User user = (User) userService.loadUserByUsername(request.getRemoteUser());
		model.addAttribute("user", user);
		return "changeProfile";
	}

	@PostMapping("/changeProfile")
	public String changeProfile(@ModelAttribute @Valid User user, BindingResult result, Model model) throws SQLException {
		logger.info(user.toString());
		if (result.hasErrors()) {
			logger.info(user.toString());
			return "changeProfile";
		} else {
			logger.info(user.toString());
			userService.save(user.toUser(passwordEncoder));
			return "redirect:/";
		}
	}
	
	 @ExceptionHandler(Exception.class)
	  public String handleIOException(Exception ex, HttpServletRequest request, Model model) {
		model.addAttribute("error", ex);
	    return "error";
	  }

}
