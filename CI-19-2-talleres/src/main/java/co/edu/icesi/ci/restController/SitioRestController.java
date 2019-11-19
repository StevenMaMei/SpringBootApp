package co.edu.icesi.ci.restController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioSitio;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

@RestController
public class SitioRestController {

	@Autowired
	private ServicioSitio servicio;
	
	@GetMapping("/api/sitios/")
	public TransactionBody<ArrayList<Tmio1Sitio>> getIndex(){
		return new TransactionBody<>("sitios",servicio.findAll());
	}
	
	@PostMapping("/api/sitios/")
	public void saveSitio(@RequestBody TransactionBody<Tmio1Sitio> sitio) throws Exception {
		servicio.guardarSitio(sitio.getBody());
	}
	@GetMapping("/api/sitios/find")
	public Tmio1Sitio findSitio(@Param("id") long id) throws Exception {
		return servicio.consultarSitio(id);
	}
	@DeleteMapping("/api/sitios")
	public void deleteSitio(@Param("id") long id) throws Exception {
		Tmio1Sitio s= findSitio(id);
		servicio.removerSitio(s);	
	}
	
	@PutMapping("/api/sitios")
	public void updateSitio(@RequestBody TransactionBody<Tmio1Sitio> sitio) throws Exception {
		servicio.actualizarSitio(sitio.getBody());
	}

}
