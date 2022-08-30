package com.proyectofinalBackEnd.demoBackEnd.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofinalBackEnd.demoBackEnd.security.Dto.JwtDto;
import com.proyectofinalBackEnd.demoBackEnd.security.Dto.LoginUsuario;
import com.proyectofinalBackEnd.demoBackEnd.security.Dto.NuevoUsuario;
import com.proyectofinalBackEnd.demoBackEnd.security.entity.rol;
import com.proyectofinalBackEnd.demoBackEnd.security.entity.usuario;
import com.proyectofinalBackEnd.demoBackEnd.security.enums.rolnombre;
import com.proyectofinalBackEnd.demoBackEnd.security.jwt.JwtProvider;
import com.proyectofinalBackEnd.demoBackEnd.security.service.UsuarioService;
import com.proyectofinalBackEnd.demoBackEnd.security.service.rolservice;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	rolservice rolService;
	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
	
			return new ResponseEntity<>(new Mensaje("Campo mal puestos o email invalido"), HttpStatus.BAD_REQUEST);

		if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity<>(new Mensaje("ya existe ese nombre"), HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity<>(new Mensaje("ya existe este email"), HttpStatus.BAD_REQUEST);
		}

		usuario user = new usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
				passwordEncoder.encode(nuevoUsuario.getPassword()));

		Set<rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(rolnombre.ROLE_USER).get());

		if (nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(rolnombre.ROLE_ADMIN).get());

		user.setRoles(roles);
		usuarioService.save(user);

		return new ResponseEntity<>(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginusuario,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("error campos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginusuario.getNombreUsuario(), loginusuario.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
		
	}

}
