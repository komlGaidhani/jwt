package com.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
	Role save(Role role);
	
}
