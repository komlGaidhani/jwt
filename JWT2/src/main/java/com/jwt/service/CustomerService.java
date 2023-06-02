package com.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.entity.User;

public interface CustomerService {
		UserDetails authenticate(String email, String password);
		User save(User c);
		User findByUserName(String username);
}
