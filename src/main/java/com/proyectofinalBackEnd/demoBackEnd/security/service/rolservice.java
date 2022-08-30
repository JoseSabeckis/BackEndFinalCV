package com.proyectofinalBackEnd.demoBackEnd.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.proyectofinalBackEnd.demoBackEnd.security.entity.rol;
import com.proyectofinalBackEnd.demoBackEnd.security.enums.rolnombre;
import com.proyectofinalBackEnd.demoBackEnd.security.repository.irolrepository;



@Service
@Transactional
@Component
public class rolservice {
	@Autowired
	irolrepository IRolRepository;
	
	public Optional<rol> getByRolNombre(rolnombre rolnombre){
		return IRolRepository.findByRolNombre(rolnombre);
	}
	
	public void save(rol rol) {
		IRolRepository.save(rol);
	}
	
}
