package com.proyectofinalBackEnd.demoBackEnd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinalBackEnd.demoBackEnd.entity.Educacion;

@Repository
public interface REducacion extends JpaRepository<Educacion, Integer>{
	public Optional<Educacion> findByNombreE(String nombreE);
	
	public boolean existByNombreE(String nombreE);
	
}
