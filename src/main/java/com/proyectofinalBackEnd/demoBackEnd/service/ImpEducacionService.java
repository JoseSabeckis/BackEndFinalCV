package com.proyectofinalBackEnd.demoBackEnd.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinalBackEnd.demoBackEnd.entity.Educacion;
import com.proyectofinalBackEnd.demoBackEnd.repository.REducacion;

@Service
@Transactional
public class ImpEducacionService {
	
	@Autowired
	REducacion rEducacion;

	public List<Educacion> list(){
		return rEducacion.findAll();
	}
	
	public Optional<Educacion> getOne(int id){
		return rEducacion.findById(id);
	}
	
	
	public Optional<Educacion>getByNombreE(String nombre){
		return rEducacion.findByNombreE(nombre);
	}
	
	public void save(Educacion educacion) {
		rEducacion.save(educacion);
	}
	
	public void delete(int id) {
		rEducacion.deleteById(id);
	}
	
	public boolean existById(int id) {
		return rEducacion.existsById(id);
	}
	
	public boolean existByNombreE(String nombre) {
		return rEducacion.existByNombreE(nombre);
	}
	
}
