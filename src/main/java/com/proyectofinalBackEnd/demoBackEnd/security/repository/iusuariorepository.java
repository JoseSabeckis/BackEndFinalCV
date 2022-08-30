package com.proyectofinalBackEnd.demoBackEnd.security.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinalBackEnd.demoBackEnd.security.entity.usuario;

@Repository
public interface iusuariorepository extends JpaRepository<usuario, Integer>{
	Optional<usuario> findByNombreUsuario(String nombreUsuario);
	
	boolean existByNombreUsuario(String nombreUsuario);
	boolean existByEmail(String email);
}
