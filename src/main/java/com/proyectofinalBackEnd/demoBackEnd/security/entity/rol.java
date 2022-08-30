package com.proyectofinalBackEnd.demoBackEnd.security.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.proyectofinalBackEnd.demoBackEnd.security.enums.rolnombre;



@Entity
public class rol {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private rolnombre rolNombre;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public rolnombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(rolnombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public rol(){
		
	}
	
	public rol(rolnombre rolname) {
		this.rolNombre = rolname;
	}
	
	

}
