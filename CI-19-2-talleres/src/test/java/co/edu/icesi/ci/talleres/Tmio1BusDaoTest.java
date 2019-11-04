package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.dao.Tmio1BusDao;
import co.edu.icesi.ci.dao.Tmio1ConductoreDao;
import co.edu.icesi.ci.dao.Tmio1RutaDao;
import co.edu.icesi.ci.dao.Tmio1ServicioDao;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Tmio1BusDaoTest {

	@Autowired
	private Tmio1BusDao tmio1BusDao;
	@Autowired
	private Tmio1ServicioDao tmio1Servicio;
	@Autowired
	private Tmio1ConductoreDao tmio1ConductoreDao;
	@Autowired
	private Tmio1RutaDao tmio1RutaDao;
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(tmio1BusDao);
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		
		tmio1BusDao.save(bus);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(tmio1BusDao);
		saveTest();
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("124");
		bus.setId(1);
		tmio1BusDao.update(bus);
		
		Tmio1Bus BusC= tmio1BusDao.findByPlaca("124");
		assertNotNull(BusC);
		assertEquals(BusC.getPlaca(), "124");
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(tmio1BusDao);	
		saveTest();
		
		Tmio1Bus BusC= tmio1BusDao.findByPlaca("123");
		assertNotNull(BusC);
		
		tmio1BusDao.delete(BusC);
		BusC= tmio1BusDao.findByPlaca("123");
		assertNull(BusC);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByMarcaTest() {

		assertNotNull(tmio1BusDao);
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal("12"));
		bus2.setMarca("maz");
		bus2.setModelo(new BigDecimal("123"));
		bus2.setTipo("A");	
		bus2.setPlaca("125");
		
		Tmio1Bus bus3 = new Tmio1Bus();
		bus3.setCapacidad(new BigDecimal("12"));
		bus3.setMarca("che");
		bus3.setModelo(new BigDecimal("123"));
		bus3.setTipo("A");	
		bus3.setPlaca("124");
		
		tmio1BusDao.save(bus);
		tmio1BusDao.save(bus2);
		tmio1BusDao.save(bus3);
		
		List<Tmio1Bus> buses = tmio1BusDao.findByMarca("asd");
		assertEquals(buses.size(), 0);
		buses= tmio1BusDao.findByMarca("che");
		
		for(Tmio1Bus b: buses) {
			assertTrue(b.getMarca().equals("che"));
		}
		assertEquals(2, buses.size());
		
		buses = tmio1BusDao.findByMarca("maz");
		for(Tmio1Bus b: buses) {
			assertTrue(b.getMarca().equals("maz"));
		}
		assertEquals(1, buses.size());
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByModeloTest() {

		assertNotNull(tmio1BusDao);
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("12"));
		bus.setMarca("che");
		bus.setModelo(new BigDecimal("123"));
		bus.setTipo("A");	
		bus.setPlaca("123");
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal("12"));
		bus2.setMarca("maz");
		bus2.setModelo(new BigDecimal("124"));
		bus2.setTipo("A");	
		bus2.setPlaca("125");
		
		tmio1BusDao.save(bus);
		tmio1BusDao.save(bus2);
		
		List<Tmio1Bus> buses = tmio1BusDao.findByModelo(new BigDecimal("123"));
		assertTrue(buses.size()== 1);
		assertTrue(buses.get(0).equals(bus));
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void consultaAdicionalTest() {

		assertNotNull(tmio1BusDao);
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
		
		tmio1BusDao.save(bus);
		tmio1BusDao.save(bus1);
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("a");
		ruta.setDescripcion("as");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal("2"));
		ruta.setHoraInicio(new BigDecimal("1"));
		ruta.setNumero("1");

		tmio1RutaDao.save(ruta);
		
		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setApellidos("ma");
		cond.setCedula("123");
		cond.setFechaContratacion(new Date(100, 10, 17));
		cond.setFechaNacimiento(new Date(99, 1, 1));
		cond.setNombre("se");
		
		tmio1ConductoreDao.save(cond);
		
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
		pk2.setIdRuta(ruta.getId());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setTmio1Bus(bus);
		s2.setId(pk2);
		s2.setTmio1Conductore(cond);
		s2.setTmio1Ruta(ruta);
		
		
		
		
		tmio1Servicio.save(s1);
		tmio1Servicio.save(s2);
		
		List<Tmio1Bus> buses = tmio1BusDao.consultaAdicional(new Date(119, 10, 25));
		assertEquals(buses.get(0).getPlaca(), "123");
		assertEquals(buses.size(), 1);
		
		
	}
	
	

}
