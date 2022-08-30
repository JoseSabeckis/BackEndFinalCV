package com.proyectofinalBackEnd.demoBackEnd.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinalBackEnd.demoBackEnd.security.entity.rol;
import com.proyectofinalBackEnd.demoBackEnd.security.enums.rolnombre;



@Repository
public interface irolrepository extends JpaRepository<rol, Integer>{
	Optional<rol> findByRolNombre(rolnombre rolNombre);
}
