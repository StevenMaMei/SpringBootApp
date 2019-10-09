package co.edu.icesi.ci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.icesi.ci.talleres.model.Tmio1User;
import co.edu.icesi.ci.talleres.repositories.ServiciosRepository;
import co.edu.icesi.ci.talleres.repositories.UserRepository;


@SpringBootApplication
public class Ci192TalleresApplication {

	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(Ci192TalleresApplication.class, args);
		
		UserRepository repos = context.getBean(UserRepository.class);

		Tmio1User user1 = new Tmio1User();
		user1.setUsername("laura");
		user1.setPassword("{noop}456");
		user1.setTipo("operador");

		Tmio1User user2 = new Tmio1User();
		user2.setUsername("steven");
		user2.setPassword("{noop}123");
		user2.setTipo("administrador");

		repos.save(user1);
		repos.save(user2);
	}

}
