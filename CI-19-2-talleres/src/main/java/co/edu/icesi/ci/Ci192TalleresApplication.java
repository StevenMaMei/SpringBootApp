package co.edu.icesi.ci;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1User;
import co.edu.icesi.ci.talleres.repositories.BusesRepository;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;
import co.edu.icesi.ci.talleres.repositories.ServiciosRepository;
import co.edu.icesi.ci.talleres.repositories.UserRepository;


@SpringBootApplication
public class Ci192TalleresApplication {

	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(Ci192TalleresApplication.class, args);
		
		UserRepository repos = context.getBean(UserRepository.class);
		ServicioBus reposB= context.getBean(ServicioBus.class);
		ServicioConductor reposC = context.getBean(ServicioConductor.class);
		ServicioRuta reposR= context.getBean(ServicioRuta.class);
		Tmio1User user1 = new Tmio1User();
		user1.setUsername("laura");
		user1.setPassword("{noop}456");
		user1.setTipo("operador");

		Tmio1User user2 = new Tmio1User();
		user2.setUsername("steven");
		user2.setPassword("{noop}123");
		user2.setTipo(Tmio1User.ROL_ADMINISTRADOR);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		
		Tmio1Conductore con = new Tmio1Conductore();
		con.setApellidos("ma");
		con.setCedula("123");
		con.setFechaContratacion(new Date(100, 10, 17));
		con.setFechaNacimiento(new Date(99, 1, 1));
		con.setNombre("se");
		
		Tmio1Ruta ru= new Tmio1Ruta();
		ru.setActiva("a");
		ru.setDescripcion("as");
		ru.setDiaFin(new BigDecimal(6));
		ru.setDiaInicio(new BigDecimal(1));
		ru.setHoraFin(new BigDecimal("2"));
		ru.setHoraInicio(new BigDecimal("1"));
		ru.setNumero("1");
		
		try {
			reposC.guardarConductor(con);
			reposB.guardarBus(bus);
			reposR.guardarRuta(ru);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repos.save(user1);
		repos.save(user2);
	}

}
