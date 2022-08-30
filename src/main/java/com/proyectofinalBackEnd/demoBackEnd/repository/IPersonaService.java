package com.proyectofinalBackEnd.demoBackEnd.repository;

import java.util.List;

import com.proyectofinalBackEnd.demoBackEnd.entity.persona;



public interface IPersonaService {

	public List<persona> getPersona();
	
	public void savePersona(persona people);
	
	public void borrarPersona(Long id);
	
	public persona buscarPersona(Long id);
	
}
