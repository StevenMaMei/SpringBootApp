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
		
		try {
			response = restTemplate.exchange(REST_URI + "/buses/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Bus>>>(){});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response.getBody().getBody();
	}
	

}
