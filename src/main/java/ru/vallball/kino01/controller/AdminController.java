package ru.vallball.kino01.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import ru.vallball.kino01.model.User;
import ru.vallball.kino01.service.UserService;

@Controller
@RequestMapping(value = { "/adminPage" })
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@ModelAttribute("user")
	public User newUser(@RequestParam(required = false, value = "id") Long id) {
		if (id == null) {
			return new User();
		} else
			return userService.findUserById(id);
	}

	@GetMapping
	public String adminPage(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
		Page<User> page = userService.findAll(pageable);
		model.addAttribute("page", page);
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "adminPage";
	}

	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}

	@PostMapping("/addUser")
	public String processRegistration(@ModelAttribute @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			logger.info("Ошибок " + result.getErrorCount() + " " + result.getAllErrors().get(0).toString());

			return "addUser";
		} else {
			logger.info("Сохраняем...");
			userService.save(user.toUser(passwordEncoder));
			return "redirect:/adminPage";
		}
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		userService.delete(id);
		return "redirect:/adminPage";
	}

	@GetMapping("/changeUser/{id}")
	public String changeUser(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
		User user = userService.findUserById(id);
		request.getSession().setAttribute("user", user);
		model.addAttribute("user", user);
		return "changeUser";
	}

	@PostMapping("/changeUser")
	public String update(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "changeUser";
		} else {

			userService.save(user.toUser(passwordEncoder));

		}
		return "redirect:/adminPage";
	}

	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request, Model model) {
		model.addAttribute("error", ex);
		return "error";
	}
}
