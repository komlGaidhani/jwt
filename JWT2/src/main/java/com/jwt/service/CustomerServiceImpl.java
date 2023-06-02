package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.CustomerDao;
import com.jwt.entity.User;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerDao customerDao;
	@Autowired
	MyUserDetailsService userDetailsService ;
	@Override
	public UserDetails authenticate(String username, String password) {
		UserDetails customer = userDetailsService.loadUserByUsername(username);
		if(customer != null && password.equals(customer.getPassword()))
			return customer;
		return null;
	}
	@Override
	public User save(User customer) {
		return customerDao.save(customer);
	}
	@Override
	public User findByUserName(String username) {
		return customerDao.findByUserName(username);
	}

}
