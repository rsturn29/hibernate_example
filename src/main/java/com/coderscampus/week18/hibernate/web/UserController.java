package com.coderscampus.week18.hibernate.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.week18.hibernate.domain.User;
import com.coderscampus.week18.hibernate.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String getAllUsers (ModelMap model) {
		
		
		//List<User> users = userService.findByUsername("rneedham322@gmail.com");
		//List<User> users = userService.findByUsernameAndName("test", "test" );
		//List<User> users = userService.findByCreatedDateBetween(LocalDate.of(2023, 03, 29),LocalDate.of(2023, 03,30 ));
		
		List<User> users = userService.findAll();
		model.put("users", users);
		if(users.size()== 1) {
		model.put("user", users.get(0));
		}
		return "users";
	}
	@GetMapping("/users/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
//		User user = userService.findExactlyOneUserByUsername("rneedham322@gmail.com");
		User user = userService.findById(userId);
		model.put("users", Arrays.asList(user));
		model.put("user", user);
		return "users";
	}
	@GetMapping("/register")
	public String createUser (ModelMap model) {
		model.put("user", new User());
		return "register";
	}
	@PostMapping("/register")
	public String postCreateUser(User user) {
		userService.saveUser(user);
		return "redirect:/register";
	}
	@PostMapping("/users/{userId}")
	public String postOneUser( User user) {
		userService.saveUser(user);
		return "redirect:/users/" + user.getUserId();
	}
	@PostMapping("/users/{userId}/delete")
	public String deleteOneUser(@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}
	
}
