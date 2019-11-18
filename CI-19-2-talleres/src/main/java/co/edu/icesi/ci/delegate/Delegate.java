package co.edu.icesi.ci.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public class Delegate {
	
	public final static String REST_URI = "localhost:8080/api";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Iterable<Tmio1Bus> getBuses(){
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
	
	

}
