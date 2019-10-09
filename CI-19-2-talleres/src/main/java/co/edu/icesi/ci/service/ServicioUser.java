package co.edu.icesi.ci.service;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1User;

public interface ServicioUser {

	public Tmio1User guardarUser(Tmio1User user) throws Exception;
	public Tmio1User removerUser(Tmio1User user)throws Exception;
	public Tmio1User actualizarUser(Tmio1User User)throws Exception;
	public Optional<Tmio1User> consultarUser(String username) throws Exception;
}
