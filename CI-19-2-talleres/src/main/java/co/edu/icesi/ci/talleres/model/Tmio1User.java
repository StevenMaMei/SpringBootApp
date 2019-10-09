package co.edu.icesi.ci.talleres.model;

import javax.persistence.Entity;

import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Tmio1User {
	
	public static final String ROL_ADMINISTRADOR ="admin";
	public static final String RROL_OPERADOR = "operador";
	@Id
	private String username;
	private String password;
	private String tipo;

}
