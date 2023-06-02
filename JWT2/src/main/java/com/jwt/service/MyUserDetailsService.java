package com.jwt.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.dao.CustomerDao;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	CustomerDao cd;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		com.jwt.entity.User user = cd.findByUserName(username);
		user.getRoles().
		forEach(role -> {
						authorities.add(new SimpleGrantedAuthority(role.getRole()));
						});
		return new User(username, user.getPassword(), authorities);
	}
}
