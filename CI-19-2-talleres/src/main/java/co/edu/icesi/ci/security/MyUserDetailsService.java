package co.edu.icesi.ci.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1User;
import co.edu.icesi.ci.talleres.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Tmio1User> u = repositorio.findById(username);
		if (u.isPresent()) {
			Tmio1User user = u.get();
			User.UserBuilder builder = User.withUsername(username).password(user.getPassword())
					.roles(user.getTipo());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	
	}

}
