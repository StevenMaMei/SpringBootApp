package co.edu.icesi.ci.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1User;
import co.edu.icesi.ci.talleres.repositories.UserRepository;
	
@Service
public class ServicioUserImp implements ServicioUser{

	@Autowired
	private UserRepository repositorio;
	

	@Override
	public Tmio1User guardarUser(Tmio1User user) throws Exception {
		if(repositorio.findById(user.getUsername()).isPresent())
		{
			throw new Exception("Usuario con username ya existente");
		}else {
			repositorio.save(user);
		}
		return user;
	}

	@Override
	public Tmio1User removerUser(Tmio1User user) throws Exception {
		repositorio.delete(user);
		return  user;
	}

	@Override
	public Tmio1User actualizarUser(Tmio1User user) throws Exception {
		
		return repositorio.save(user);
	}

	@Override
	public Optional<Tmio1User> consultarUser(String username) throws Exception {
		return repositorio.findById(username);
	}

}
