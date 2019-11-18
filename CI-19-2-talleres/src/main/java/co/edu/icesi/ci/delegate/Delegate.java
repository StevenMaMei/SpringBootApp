package co.edu.icesi.ci.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public class Delegate {

	public final static String REST_URI = "localhost:8080/api";

	@Autowired
	private RestTemplate restTemplate;

	public Iterable<Tmio1Bus> getBuses() {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Bus>>> response = null;
		response = restTemplate.exchange(REST_URI + "/buses/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Bus>>>(){});
		return response.getBody().getBody();
	}
	
	public void saveBus(Tmio1Bus bus) {
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1Bus> transaction= new TransactionBody<>("apiContext",bus);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = null;
		response = restTemplate.exchange(REST_URI + "/buses/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>(){});
		
	}
	
	public Iterable<Tmio1Bus> buscarBus(String placa) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Bus>>> response = null;
		response = restTemplate.exchange(REST_URI + "/buses/search/findByPlaca?placa="+placa, HttpMethod.GET, request,  new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Bus>>>(){});
		return response.getBody().getBody();
	}

	public Iterable<Tmio1Conductore> getConductores() throws Exception {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Conductore>>> response = null;
		response = restTemplate.exchange(REST_URI + "/conductores/", HttpMethod.GET, request,
				new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Conductore>>>() {
				});
		return response.getBody().getBody();
	}

	public void saveConductor(Tmio1Conductore conductor) throws Exception {
		HttpHeaders headerAct = new HttpHeaders();

		TransactionBody<Tmio1Conductore> transaction = new TransactionBody<>("apiContext", conductor);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
		response = restTemplate.exchange(REST_URI + "/conductores/", HttpMethod.POST, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
				});
		response.getBody();
	}

	public Tmio1Conductore findConductorByCedula(String cedula) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
		// Posiblemente haya que cambiar esto y lo de los RestControllers si el profe
		// quiere que consigan datos tipo conductores/{id}. Esto se
		// realizo en el ejemplo mostrado en clase.
		response = restTemplate.exchange(REST_URI + "/conductores/search/findByCedula?cedula=" + cedula, HttpMethod.GET,
				request, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
				});
		return response.getBody().getBody();
	}

	public Iterable<Tmio1Ruta> getRutas() {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Ruta>>> response = null;
		response = restTemplate.exchange(REST_URI + "/rutas/", HttpMethod.GET, request,
				new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Ruta>>>() {
				});
		return response.getBody().getBody();
	}

	public void saveRuta(Tmio1Ruta ruta) {
		HttpHeaders headerAct = new HttpHeaders();

		TransactionBody<Tmio1Ruta> transaction = new TransactionBody<>("apiContext", ruta);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		response = restTemplate.exchange(REST_URI + "/rutas/", HttpMethod.POST, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
				});
		response.getBody();
	}
	
	public Tmio1Ruta findRutaByDescripcion(String descripcion) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		response = restTemplate.exchange(REST_URI + "/rutas/search/findByDescripcion?descripcion=" + descripcion, HttpMethod.GET,
				request, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
				});
		return response.getBody().getBody();
	}
	
	
	
	

}
