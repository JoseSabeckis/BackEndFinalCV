package com.proyectofinalBackEnd.demoBackEnd.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofinalBackEnd.demoBackEnd.dto.dtoEducacion;
import com.proyectofinalBackEnd.demoBackEnd.entity.Educacion;
import com.proyectofinalBackEnd.demoBackEnd.security.controller.Mensaje;
import com.proyectofinalBackEnd.demoBackEnd.service.ImpEducacionService;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {
	@Autowired
	ImpEducacionService sEducacion;
	
	@GetMapping("lista")
	public ResponseEntity<List<Educacion>> list(){
		List<Educacion> list = sEducacion.list();
		
		return new ResponseEntity<List<Educacion>>(list, HttpStatus.OK);		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/detail/{id}")
	public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
		if(!sEducacion.existById(id)) {
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.BAD_REQUEST);
		}
		
		Educacion educacion = sEducacion.getOne(id).get();
		
		return new ResponseEntity<Educacion>(educacion, HttpStatus.OK);
		
	} 
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>delete(@PathVariable("id") int id){
		if(!sEducacion.existById(id)) {
			return new ResponseEntity<>(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
		}
		
		sEducacion.delete(id);
		
		return new ResponseEntity<>(new Mensaje("educacion eliminada"), HttpStatus.OK);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){
		if(StringUtils.isBlank(dtoeducacion.getNombreE())){
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if(sEducacion.existByNombreE(dtoeducacion.getNombreE())) {
			return new ResponseEntity<>(new Mensaje("ya existe ese nombre"), HttpStatus.BAD_REQUEST);
		}
		
		Educacion educacion = new Educacion(
				dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE());
		sEducacion.save(educacion);
		
		return new ResponseEntity<>(new Mensaje("Educacion creada"), HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion){
		if(!sEducacion.existById(id)) {
			return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.NOT_FOUND);
		}
		
		if(sEducacion.existByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id) {
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		}
		
		if(StringUtils.isBlank(dtoeducacion.getNombreE())) {
			return new ResponseEntity<>(new Mensaje("el campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
		}
		
		Educacion educacion = sEducacion.getOne(id).get();
		
		educacion.setNombreE(dtoeducacion.getNombreE());
		educacion.setDescripcionE(dtoeducacion.getDescripcionE());
		
		sEducacion.save(educacion);
		
		return new ResponseEntity<>(new Mensaje("educacion actualizada"), HttpStatus.OK);
		
	}
	
	
	
	
}
