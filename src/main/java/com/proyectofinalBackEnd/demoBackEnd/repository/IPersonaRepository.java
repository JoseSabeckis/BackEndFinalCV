package com.proyectofinalBackEnd.demoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinalBackEnd.demoBackEnd.entity.persona;



@Repository
public interface IPersonaRepository extends JpaRepository<persona, Long>{

}
