package com.proyectofinalBackEnd.demoBackEnd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class usuario implements Serializable{

	private static final long serialVersionUID = -605801432008059599L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
	@Column(length=50, nullable=false, unique = true)
	private String username;
	
	@Column(length=60, nullable=false)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
