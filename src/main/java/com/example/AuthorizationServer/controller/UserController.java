package com.example.AuthorizationServer.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthorizationServer.models.Role;
import com.example.AuthorizationServer.models.User;
import com.example.AuthorizationServer.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@GetMapping("/save")
	public User create() {
		User user = new User();
		user.setUserName("john");
		user.setPassword(encoder.encode("john12$"));
		user.setEnabled(true);
		Role role = new Role();
		Set<Role> roles = new HashSet<>();
		role.setName("user");
		roles.add(role);
		user.setRoles(roles);
		return repository.saveAndFlush(user);
	}
	
}
