package co.edu.icesi.ci.talleres;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;
import co.edu.icesi.ci.talleres.repositories.ServiciosRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ci192TalleresApplicationTests {

	
	@Autowired
	private RutasRepository rutaRepository;
	
		
	@Test
	public void contextLoads() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		rutaRepository.save(ruta);
	}

}
