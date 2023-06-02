package com.jwt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.dao.CustomerDao;
import com.jwt.dao.RoleDao;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.service.CustomerService;
import com.jwt.util.JwtUtils;

@RequestMapping("/user")
@RestController
public class LoginController {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);  
	@Autowired
	CustomerService cs;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	private JwtUtils jwtTokenUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User cred){
		logger.info("inside /login");  
		if (cs.findByUserName(cred.getUserName()) == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not register" );
		UserDetails userdetils = cs.authenticate(cred.getUserName(), cred.getPassword());
		if(userdetils != null) {
			final String jwt = jwtTokenUtils.generateToken(userdetils);
			return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong password" );	
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers(){
		
		return ResponseEntity.ok(customerDao.findAll());
	}
	
	@GetMapping("/getRoles")
	public ResponseEntity<?> getRoles(){
		
		return ResponseEntity.ok(roleDao.findAll());
	}
	
	@PostMapping("/addRole")
	public ResponseEntity<?> addRole(@RequestBody Role role){
		return ResponseEntity.ok(roleDao.save(role));
	}
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}
