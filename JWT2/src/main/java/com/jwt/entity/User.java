package com.jwt.entity;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter@Setter@NoArgsConstructor
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "first_name")
	private String userName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	@Column(name = "phone_no")
	private String phoneNo;
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<Role>();
}
