package com.proyectofinalBackEnd.demoBackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofinalBackEnd.demoBackEnd.entity.persona;
import com.proyectofinalBackEnd.demoBackEnd.repository.IPersonaService;



@RestController
//CrossOrigin(origins="http://localhost:4200")sacar el parentesis para importar
public class PersonaController {

	@Autowired IPersonaService personaService;
	
	@GetMapping("personas/traer")
	public List<persona> getPersona(){
		return personaService.getPersona();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/personas/crear")
	public String crearPersonas(@RequestBody persona people) {
		personaService.savePersona(people);
		return "Se Guardaron Los Cambios";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/personas/borrar/{id}")
	public String borrarPersona(@PathVariable Long id) {
		personaService.borrarPersona(id);
		return "Se Borro La Persona";
	}
	
	@PutMapping("/personas/modificar/{id}")
	public persona modificarPersona(@PathVariable Long id, 
			                        @RequestParam("nombre") String nuevoNombre,
			                        @RequestParam("apellido") String nuevoApellido,
			                        @RequestParam("imagen") String nuevoImagen) {
		
		persona people = personaService.buscarPersona(id);
		
		people.setNombre(nuevoNombre);
		people.setApellido(nuevoApellido);
		people.setImagen(nuevoImagen);
		
		personaService.savePersona(people);
		
		return people;
		
	}
	
	@GetMapping("/personas/traer/perfil")
	public persona buscarPersona() {
		return personaService.buscarPersona((long)1);
	}
	
}
