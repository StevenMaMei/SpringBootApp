package co.edu.icesi.ci.delegate;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioWrapper;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

@Component
public class Delegate {

	public final static String REST_URI = "http://localhost:8080/api";

	private RestTemplate restTemplate = new RestTemplate();

	public ArrayList<Tmio1Sitio> getSitios(){
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<ArrayList<Tmio1Sitio>>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/sitios/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<ArrayList<Tmio1Sitio>>>(){});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		return response.getBody().getBody();
	}
	public void saveSitio(Tmio1Sitio sitio) throws Exception {
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1Sitio> transaction= new TransactionBody<>("apiContext",sitio);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Object>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/sitios/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Object>>(){});			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
	}
	public void updateSitio(Tmio1Sitio sitio) {
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1Sitio> transaction= new TransactionBody<>("apiContext",sitio);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/sitios/", HttpMethod.PUT, request, new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>(){});			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
	}
	public ArrayList<Tmio1Bus> getBuses() {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<ArrayList<Tmio1Bus>>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/buses/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<ArrayList<Tmio1Bus>>>(){});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		return response.getBody().getBody();
	}
	
	public void deleteSitio(long id) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/sitios?id="+id, HttpMethod.DELETE, request,  new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>(){});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		
	}
	
	public ArrayList<Tmio1Sitio> findSitio(long id) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/sitios/find?id="+id, HttpMethod.GET, request,  new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>(){});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		ArrayList<Tmio1Sitio> s= new ArrayList<>();
		s.add(response.getBody().getBody());
		return s;
	}
	
	public void saveBus(Tmio1Bus bus) throws Exception {
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Object> transaction= new TransactionBody<>("apiContext",bus);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Object>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/buses/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Object>>(){});			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		if(response.getBody().getApiContext().equals("exception")) {
			throw new Exception ((String)response.getBody().getBody());
		}
		
	}
	
	public Iterable<Tmio1Bus> buscarBus(String placa) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Bus>>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/buses/search/findByPlaca?placa="+placa, HttpMethod.GET, request,  new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Bus>>>(){});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		
		return response.getBody().getBody();
	}
	
	public Iterable<Tmio1Servicio> getServicios(){
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Servicio>>> response = null;
		response = restTemplate.exchange(REST_URI + "/servicios/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Servicio>>>(){});
		return response.getBody().getBody();
	}
	
	public void saveServicio(Tmio1ServicioWrapper w) throws Exception {
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Object> transaction= new TransactionBody<>("apiContext",w);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Object>> response = null;
		response = restTemplate.exchange(REST_URI + "/servicios/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Object>>(){});
		if(response.getBody().getApiContext().equals("exception")) {
			throw new Exception((String) response.getBody().getBody());
		}
	}
	
	public void updateServicio(Tmio1ServicioWrapper w) throws Exception {
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Object> transaction= new TransactionBody<>("apiContext",w);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Object>> response = null;
		response = restTemplate.exchange(REST_URI + "/servicios/", HttpMethod.PUT, request, new ParameterizedTypeReference<TransactionBody<Object>>(){});
		if(response.getBody().getApiContext().equals("exception")) {
			throw new Exception((String) response.getBody().getBody());
		}
	}
	
	public Iterable<Tmio1Servicio> findServicioByDate(Date fecha) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Servicio>>> response = null;
		response = restTemplate.exchange(REST_URI + "/buses/search/find?fecha="+fecha, HttpMethod.GET, request,  new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Servicio>>>(){});
		return response.getBody().getBody();
	}
	public Iterable<Tmio1Conductore> getConductores() {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Conductore>>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/conductores/", HttpMethod.GET, request,
					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Conductore>>>() {
			});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		return response.getBody().getBody();
	}

	public void saveConductor(Tmio1Conductore conductor) throws Exception {
		HttpHeaders headerAct = new HttpHeaders();

		TransactionBody<Object> transaction = new TransactionBody<>("apiContext", conductor);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Object>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/conductores/", HttpMethod.POST, request,
					new ParameterizedTypeReference<TransactionBody<Object>>() {
			});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		
		if(response.getBody().getApiContext().equals("exception")) {
			throw new Exception((String) response.getBody().getBody());
		}
	}

	public Tmio1Conductore findConductorByCedula(String cedula) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
		// Posiblemente haya que cambiar esto y lo de los RestControllers si el profe
		// quiere que consigan datos tipo conductores/{id}. Esto se
		// realizo en el ejemplo mostrado en clase.
		try {
			response = restTemplate.exchange(REST_URI + "/conductores/search/findByCedula?cedula=" + cedula, HttpMethod.GET,
					request, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
			});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		return response.getBody().getBody();
	}

	public Iterable<Tmio1Ruta> getRutas() {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Iterable<Tmio1Ruta>>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/rutas/", HttpMethod.GET, request,
					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Ruta>>>() {
			});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		return response.getBody().getBody();
	}

	public void saveRuta(Tmio1Ruta ruta) {
		HttpHeaders headerAct = new HttpHeaders();

		TransactionBody<Tmio1Ruta> transaction = new TransactionBody<>("apiContext", ruta);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/rutas/", HttpMethod.POST, request,
					new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
			});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
	}
	
	public Tmio1Ruta findRutaByDescripcion(String descripcion) {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		try {
			response = restTemplate.exchange(REST_URI + "/rutas/search/findByDescripcion?descripcion=" + descripcion, HttpMethod.GET,
					request, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
			});
			
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		return response.getBody().getBody();
	}
	
	
	
	

}
