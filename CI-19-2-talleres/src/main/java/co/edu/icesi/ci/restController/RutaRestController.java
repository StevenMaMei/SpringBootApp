package co.edu.icesi.ci.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.delegate.TransactionBody;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

@RestController
public class RutaRestController {
	
	@Autowired
	private ServicioRuta servicio;
	
	@GetMapping("/api/rutas/")
	public TransactionBody<Iterable<Tmio1Ruta>> getRutas(){
		return new TransactionBody<>("rutas",servicio.findAll());
	}
	
	@PostMapping("/api/rutas/")
	public void addRuta(@RequestBody TransactionBody<Tmio1Ruta> ruta) throws Exception{
		servicio.guardarRuta(ruta.getBody());
	}
	
	@GetMapping("/api/rutas/search/findByDescripcion")
	public TransactionBody<Iterable<Tmio1Ruta>> findByDescripcion(@Param("descripcion") String descripcion){
		return new TransactionBody<>("rutas", servicio.findByDescripcion(descripcion));
	}

}
