package co.edu.icesi.ci.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.restController.BusRestController;
import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.service.ServicioServicio;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DelegateTest {

	@Mock
	RestTemplate rest;
	
	@Autowired
	@InjectMocks
	Delegate delegado;
	
	public final static String REST_URI = "http://localhost:8080/api";

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBuses() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal("12"));
		bus1.setMarca("che");
		bus1.setModelo(new BigDecimal("123"));
		bus1.setTipo("A");	
		bus1.setPlaca("124");
		ArrayList<Tmio1Bus> buses = new ArrayList<>();
		buses.add(bus1);
		buses.add(bus);
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);

		
		when(rest.exchange(REST_URI + "/buses/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<ArrayList<Tmio1Bus>>>(){})).thenReturn(new ResponseEntity<TransactionBody<ArrayList<Tmio1Bus>>>(new TransactionBody("buses",buses), HttpStatus.ACCEPTED));
		
		ArrayList<Tmio1Bus> res= delegado.getBuses();
		assertTrue(res.size()==2);
		assertEquals(res.get(0), bus1);
		assertEquals(res.get(1), bus);
	}

}
