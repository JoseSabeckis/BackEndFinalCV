package com.proyectofinalBackEnd.demoBackEnd.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyectofinalBackEnd.demoBackEnd.security.entity.usuario;
import com.proyectofinalBackEnd.demoBackEnd.security.entity.usuarioprincipal;


@Service
public class userdetailsimple implements UserDetailsService{
	@Autowired
	UsuarioService UsuarioService;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		usuario user = UsuarioService.getByNombreUsuario(nombreUsuario).get();
		
		return usuarioprincipal.build(user);
	}
	
	
}
