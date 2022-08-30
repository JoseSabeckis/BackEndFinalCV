package com.proyectofinalBackEnd.demoBackEnd.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofinalBackEnd.demoBackEnd.dto.dtoExperiencia;
import com.proyectofinalBackEnd.demoBackEnd.entity.Experiencia;
import com.proyectofinalBackEnd.demoBackEnd.security.controller.Mensaje;
import com.proyectofinalBackEnd.demoBackEnd.service.ImpExperienciaService;

@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
	@Autowired
	ImpExperienciaService sExperiencia;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Experiencia>> list(){
		List<Experiencia> list = sExperiencia.list();	
		return new ResponseEntity<List<Experiencia>>(list, HttpStatus.OK);//verificar si va <List<Experiencia>>
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
		if(StringUtils.isBlank(dtoexp.getNombreE()))
			return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if(sExperiencia.existByNombreExperiencia(dtoexp.getNombreE()))
			return new ResponseEntity<>(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
		Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescricionE());
		sExperiencia.save(experiencia);
		
		return new ResponseEntity<>(new Mensaje("Experiencia Guardada"), HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
		if(!sExperiencia.existById(id))
			return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.BAD_REQUEST);
		if(sExperiencia.existByNombreExperiencia(dtoexp.getNombreE()) && 
		   sExperiencia.getByNombreExperiencia(dtoexp.getNombreE()).get().getId() != id)
		return new ResponseEntity<>(new Mensaje("la experiencia ya existe"), HttpStatus.BAD_REQUEST);
		if(StringUtils.isBlank(dtoexp.getNombreE()))
			return new ResponseEntity<>(new Mensaje("El Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Experiencia experiencia = sExperiencia.getOne(id).get();
		experiencia.setNombreExperiencia(dtoexp.getNombreE());
		experiencia.setDescripcionExperiencia(dtoexp.getDescricionE());
		
		sExperiencia.save(experiencia);;
		
		return new ResponseEntity<>(new Mensaje("Experiencia Actualizada"), HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		if(!sExperiencia.existById(id))
			return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.BAD_REQUEST);
		sExperiencia.delete(id);
		return new ResponseEntity<>(new Mensaje("Experiencia Eliminada"), HttpStatus.OK);
	}
	
	

}
