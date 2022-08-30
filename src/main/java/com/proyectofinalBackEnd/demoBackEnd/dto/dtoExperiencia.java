package com.proyectofinalBackEnd.demoBackEnd.dto;

import javax.validation.constraints.NotBlank;

public class dtoExperiencia {
	@NotBlank
	private String nombreE;
	@NotBlank
	private String descricionE;
	
	public dtoExperiencia() {
	}

	public dtoExperiencia(@NotBlank String nombreE, @NotBlank String descricionE) {
		super();
		this.nombreE = nombreE;
		this.descricionE = descricionE;
	}

	public String getNombreE() {
		return nombreE;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}

	public String getDescricionE() {
		return descricionE;
	}

	public void setDescricionE(String descricionE) {
		this.descricionE = descricionE;
	}
	
	
	
}
