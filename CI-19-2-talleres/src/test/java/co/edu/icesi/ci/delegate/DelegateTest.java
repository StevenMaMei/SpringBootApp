package co.edu.icesi.ci.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
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
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioWrapper;
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
	
	@Test
	public void testGetConductores() {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1140");
		conductor.setNombre("Alan");
		conductor.setApellidos("Gore");
		conductor.setFechaNacimiento(Date.valueOf("1996-04-29"));
		conductor.setFechaNacimiento(Date.valueOf("2019-01-01"));
		
		Tmio1Conductore conductor1 = new Tmio1Conductore();
		conductor1.setCedula("9604");
		conductor1.setNombre("Stev");
		conductor1.setApellidos("Univ");
		conductor1.setFechaNacimiento(Date.valueOf("1998-02-02"));
		conductor1.setFechaNacimiento(Date.valueOf("2019-02-01"));
		
		ArrayList<Tmio1Conductore> conductores = new ArrayList<>();
		
		conductores.add(conductor);
		conductores.add(conductor1);
		
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		
		when(rest.exchange(REST_URI + "/conductores/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Conductore>>>(){})).thenReturn(new ResponseEntity<TransactionBody<Iterable<Tmio1Conductore>>>(new TransactionBody("conductores",conductores), HttpStatus.ACCEPTED));

		
		ArrayList<Tmio1Conductore> res = (ArrayList) delegado.getConductores();
		assertTrue(res.size()==2);
		assertEquals(conductor, res.get(0));
		assertEquals(conductor1, res.get(1));
	}
	
	@Test
	public void testSaveConductor() {
		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setApellidos("ma");
		cond.setCedula("123");
		cond.setFechaContratacion(new Date(100, 10, 17));
		cond.setFechaNacimiento(new Date(99, 1, 1));
		cond.setNombre("se");
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1Conductore> transaction= new TransactionBody<>("apiContext",cond);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
		when(rest.exchange(REST_URI + "/conductores/", HttpMethod.POST, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
		})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Conductore>>(new TransactionBody<>(),HttpStatus.ACCEPTED));
		try {
			delegado.saveConductor(cond);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testFindConductorByCedula() {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("ma");
		conductor.setCedula("123");
		conductor.setFechaContratacion(new Date(100, 10, 17));
		conductor.setFechaNacimiento(new Date(99, 1, 1));
		conductor.setNombre("se");
				
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		
		when(rest.exchange(REST_URI + "/conductores/search/findByCedula?cedula=123", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>(){})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Conductore>>(new TransactionBody("conductor",conductor), HttpStatus.ACCEPTED));
		
		Tmio1Conductore res = delegado.findConductorByCedula("123");
		assertEquals(conductor, res);
		
	}
	
	@Test
	public void testGetRutas() {
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("a");
		ruta.setDescripcion("a");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal("2"));
		ruta.setHoraInicio(new BigDecimal("1"));
		ruta.setNumero("1");
		
		Tmio1Ruta ruta2= new Tmio1Ruta();
		ruta2.setActiva("a");
		ruta2.setDescripcion("b");
		ruta2.setDiaFin(new BigDecimal(6));
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal("2"));
		ruta2.setHoraInicio(new BigDecimal("1"));
		ruta2.setNumero("1");
		
		ArrayList<Tmio1Ruta> rutas = new ArrayList<>();
		rutas.add(ruta);
		rutas.add(ruta2);
		
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		
		when(rest.exchange(REST_URI + "/rutas/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Ruta>>>(){})).thenReturn(new ResponseEntity<TransactionBody<Iterable<Tmio1Ruta>>>(new TransactionBody("rutas",rutas), HttpStatus.ACCEPTED));

		ArrayList<Tmio1Ruta> res = (ArrayList) delegado.getRutas();
		assertTrue(res.size()==2);
		assertEquals(ruta, res.get(0));
		assertEquals(ruta2, res.get(1));
		
	}
	
	@Test
	public void testSaveRuta() {
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("a");
		ruta.setDescripcion("a");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal("2"));
		ruta.setHoraInicio(new BigDecimal("1"));
		ruta.setNumero("1");
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1Ruta> transaction= new TransactionBody<>("apiContext",ruta);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		when(rest.exchange(REST_URI + "/rutas/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>(){})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Ruta>>(new TransactionBody<>(),HttpStatus.ACCEPTED));
		try {
			delegado.saveRuta(ruta);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testFindRutaByDescripcion () {
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("a");
		ruta.setDescripcion("a");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal("2"));
		ruta.setHoraInicio(new BigDecimal("1"));
		ruta.setNumero("1");
				
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		
		when(rest.exchange(REST_URI + "/rutas/search/findByDescripcion?descripcion=a", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>(){})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Ruta>>(new TransactionBody("ruta",ruta), HttpStatus.ACCEPTED));
		Tmio1Ruta res = delegado.findRutaByDescripcion("a");
		assertEquals(ruta, res);
	
	}
	
	@Test
	public void saveBusTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1Bus> transaction= new TransactionBody<>("apiContext",bus);
		HttpEntity request = new HttpEntity(transaction);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = null;
		when(rest.exchange(REST_URI + "/buses/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>(){})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Bus>>(new TransactionBody<>(),HttpStatus.ACCEPTED));
		try {
			delegado.saveBus(bus);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void buscarBusTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		ArrayList<Tmio1Bus> buses = new ArrayList<>();
		buses.add(bus);
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		when(rest.exchange(REST_URI + "/buses/search/findByPlaca?placa=123", HttpMethod.GET, request,  new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Bus>>>(){})).thenReturn(new ResponseEntity<TransactionBody<Iterable<Tmio1Bus>>>(new TransactionBody<>("a",buses),HttpStatus.ACCEPTED));
		
		ArrayList<Tmio1Bus> buss = (ArrayList<Tmio1Bus>) delegado.buscarBus("123");
		assertTrue(buss.size()==1);
		assertTrue(buss.get(0).getPlaca().equals("123"));
	}
	
	@Test
	public void getServicios() {
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
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("a");
		ruta.setDescripcion("a");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal("2"));
		ruta.setHoraInicio(new BigDecimal("1"));
		ruta.setNumero("1");
		
		Tmio1Ruta ruta2= new Tmio1Ruta();
		ruta2.setActiva("a");
		ruta2.setDescripcion("b");
		ruta2.setDiaFin(new BigDecimal(6));
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal("2"));
		ruta2.setHoraInicio(new BigDecimal("1"));
		ruta2.setNumero("1");
		
		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setApellidos("ma");
		cond.setCedula("123");
		cond.setFechaContratacion(new Date(100, 10, 17));
		cond.setFechaNacimiento(new Date(99, 1, 1));
		cond.setNombre("se");
		
		Tmio1ServicioPK pk1= new Tmio1ServicioPK();
		pk1.setIdBus(bus.getId());
		pk1.setFechaInicio(new Date(119, 10, 25));
		pk1.setFechaFin(new Date(119,10,31));
		pk1.setCedulaConductor(cond.getCedula());
		pk1.setIdRuta(ruta.getId());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setTmio1Bus(bus);
		s1.setId(pk1);
		s1.setTmio1Conductore(cond);
		s1.setTmio1Ruta(ruta);
		
		Tmio1ServicioPK pk2= new Tmio1ServicioPK();
		pk2.setIdBus(bus.getId());
		pk2.setFechaInicio(new Date(119, 10, 20));
		pk2.setFechaFin(new Date(119,10,31));
		pk2.setCedulaConductor(cond.getCedula());
		pk2.setIdRuta(ruta2.getId());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setTmio1Bus(bus1);
		s2.setId(pk2);
		s2.setTmio1Conductore(cond);
		s2.setTmio1Ruta(ruta2);
		
		ArrayList<Tmio1Servicio> ser= new ArrayList<>();
		ser.add(s1);
		ser.add(s2);
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
		when( rest.exchange(REST_URI + "/servicios/", HttpMethod.GET, request, new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Servicio>>>(){})).thenReturn(new ResponseEntity<TransactionBody<Iterable<Tmio1Servicio>>>(new TransactionBody<>("ajajjaja" ,ser) , HttpStatus.ACCEPTED));
	
		ArrayList<Tmio1Servicio> servicios = (ArrayList<Tmio1Servicio>) delegado.getServicios();
		assertTrue(servicios.size() == 2);
		assertTrue(servicios.get(0).getTmio1Bus().getPlaca().equals("123"));
		assertTrue(servicios.get(1).getTmio1Bus().getPlaca().equals("124"));
	}
	
	@Test
	public void saveServicioTest() {
		Tmio1ServicioWrapper w= new Tmio1ServicioWrapper();
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1ServicioWrapper> transaction= new TransactionBody<>("apiContext",w);
		HttpEntity request = new HttpEntity(transaction);
		w.setCedulaConductor("123");
		w.setFechaFin(new java.sql.Date(119, 1,1));
		w.setFechaInicio(new Date(118,1,1));
		w.setIdBus("123");
		w.setRutaId("1");
		when(rest.exchange(REST_URI + "/servicios/", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>(){})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Servicio>>(new TransactionBody<>(), HttpStatus.ACCEPTED));
		try {
			delegado.saveServicio(w);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void updateServicioTest() {
		Tmio1ServicioWrapper w= new Tmio1ServicioWrapper();
		w.setCedulaConductor("124");
		w.setFechaFin(new java.sql.Date(119, 1,1));
		w.setFechaInicio(new Date(118,1,1));
		w.setIdBus("123");
		w.setRutaId("1");
		w.setCedulaConductorViejo("123");
		w.setFechaFinViejo(new java.sql.Date(119, 1,1));
		w.setFechaInicioViejo(new Date(118,1,1));
		w.setIdBusViejo("123");
		w.setRutaIdViejo("1");
		
		HttpHeaders headerAct = new HttpHeaders();
		TransactionBody<Tmio1ServicioWrapper> transaction= new TransactionBody<>("apiContext",w);
		HttpEntity request = new HttpEntity(transaction);
		when(rest.exchange(REST_URI + "/servicios/", HttpMethod.PUT, request, new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>(){})).thenReturn(new ResponseEntity<TransactionBody<Tmio1Servicio>>(new TransactionBody<>(), HttpStatus.ACCEPTED));
		
		try {
			delegado.updateServicio(w);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void findServicioByDate() {
		HttpHeaders headerAct = new HttpHeaders();
		HttpEntity request = new HttpEntity(headerAct);
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
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("a");
		ruta.setDescripcion("a");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal("2"));
		ruta.setHoraInicio(new BigDecimal("1"));
		ruta.setNumero("1");
		
		Tmio1Ruta ruta2= new Tmio1Ruta();
		ruta2.setActiva("a");
		ruta2.setDescripcion("b");
		ruta2.setDiaFin(new BigDecimal(6));
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal("2"));
		ruta2.setHoraInicio(new BigDecimal("1"));
		ruta2.setNumero("1");
		
		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setApellidos("ma");
		cond.setCedula("123");
		cond.setFechaContratacion(new Date(100, 10, 17));
		cond.setFechaNacimiento(new Date(99, 1, 1));
		cond.setNombre("se");
		
		Tmio1ServicioPK pk1= new Tmio1ServicioPK();
		pk1.setIdBus(bus.getId());
		pk1.setFechaInicio(new Date(119, 10, 25));
		pk1.setFechaFin(new Date(119,10,31));
		pk1.setCedulaConductor(cond.getCedula());
		pk1.setIdRuta(ruta.getId());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setTmio1Bus(bus);
		s1.setId(pk1);
		s1.setTmio1Conductore(cond);
		s1.setTmio1Ruta(ruta);
		
		Tmio1ServicioPK pk2= new Tmio1ServicioPK();
		pk2.setIdBus(bus.getId());
		pk2.setFechaInicio(new Date(119, 10, 20));
		pk2.setFechaFin(new Date(119,10,31));
		pk2.setCedulaConductor(cond.getCedula());
		pk2.setIdRuta(ruta2.getId());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setTmio1Bus(bus1);
		s2.setId(pk2);
		s2.setTmio1Conductore(cond);
		s2.setTmio1Ruta(ruta2);
		ArrayList<Tmio1Servicio> ser= new ArrayList<>();
		ser.add(s2);
		Date fecha = new Date(119,10,20);
		
		when( rest.exchange(REST_URI + "/buses/search/find?fecha="+fecha, HttpMethod.GET, request,  new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Servicio>>>(){})).thenReturn(new ResponseEntity<TransactionBody<Iterable<Tmio1Servicio>>>(new TransactionBody<>("ejje",ser), HttpStatus.ACCEPTED));
		
		ArrayList<Tmio1Servicio> con= (ArrayList<Tmio1Servicio>) delegado.findServicioByDate(fecha);
		assertTrue(con.get(0)==s2);
	
	}

}
