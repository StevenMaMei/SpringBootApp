package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
public class Tmio1ConductoreDaoTest {

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

		assertNotNull(tmio1ConductoreDao);
		
		Tmio1Conductore con = new Tmio1Conductore();
		con.setApellidos("ma");
		con.setCedula("123");
		con.setFechaContratacion(new Date(100, 10, 17));
		con.setFechaNacimiento(new Date(99, 1, 1));
		con.setNombre("se");
		
		tmio1ConductoreDao.save(con);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(tmio1ConductoreDao);
		
		Tmio1Conductore con = new Tmio1Conductore();
		con.setApellidos("ma");
		con.setCedula("123");
		con.setFechaContratacion(new Date(100, 10, 17));
		con.setFechaNacimiento(new Date(99, 1, 1));
		con.setNombre("se");
		
		Tmio1Conductore con1 = new Tmio1Conductore();
		con1.setApellidos("ma");
		con1.setCedula("4123");
		con1.setFechaContratacion(new Date(100, 10, 17));
		con1.setFechaNacimiento(new Date(99, 1, 1));
		con1.setNombre("se");
		
		tmio1ConductoreDao.save(con);
		List<Tmio1Conductore> cons = tmio1ConductoreDao.findAll();
		assertTrue(cons.size()==1);
		tmio1ConductoreDao.save(con1);
		cons = tmio1ConductoreDao.findAll();
		assertTrue(cons.size()==2);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(tmio1ConductoreDao);
		saveTest();
		
		Tmio1Conductore con = new Tmio1Conductore();
		con.setApellidos("mei");
		con.setCedula("123");
		con.setFechaContratacion(new Date(100, 10, 17));
		con.setFechaNacimiento(new Date(99, 1, 1));
		con.setNombre("se");
		
		tmio1ConductoreDao.update(con);
		
		assertTrue(tmio1ConductoreDao.findByCedula("123").getApellidos().equals(con.getApellidos()));
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(tmio1ConductoreDao);
		saveTest();
		
		Tmio1Conductore c= tmio1ConductoreDao.findByCedula("123");
		assertNotNull(c);
		
		tmio1ConductoreDao.delete(c);
		try {
			c=tmio1ConductoreDao.findByCedula("123");
			fail();
		}catch(Exception e) {
			
		}
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByCedulaTest() {

		assertNotNull(tmio1ConductoreDao);
		saveTest();
		
		Tmio1Conductore c= tmio1ConductoreDao.findByCedula("123");
		assertNotNull(c);
		try {
			
			c=tmio1ConductoreDao.findByCedula("124");
			fail();
		}catch(Exception e) {
			
		}
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByNombreTest() {

		assertNotNull(tmio1ConductoreDao);
		saveTest();
		
		List<Tmio1Conductore> c= tmio1ConductoreDao.findByNombre("se");
		assertEquals(c.get(0).getCedula(), "123");
		assertEquals(c.size(), 1);
		
		 c= tmio1ConductoreDao.findByNombre("ses");
		assertEquals(c.size(), 0);

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByApellidosTest() {

		assertNotNull(tmio1ConductoreDao);
		saveTest();
		List<Tmio1Conductore> c= tmio1ConductoreDao.findByApellidos("ma");
		assertEquals(c.get(0).getCedula(), "123");
		assertEquals(c.size(), 1);
		
		c= tmio1ConductoreDao.findByNombre("mei");
		assertEquals(c.size(), 0);
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void consultaAdicionalTest() {

		assertNotNull(tmio1ConductoreDao);
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
		
		Tmio1Conductore cond2= new Tmio1Conductore();
		cond2.setApellidos("mei");
		cond2.setCedula("124");
		cond2.setFechaContratacion(new Date(100, 10, 17));
		cond2.setFechaNacimiento(new Date(99, 1, 12));
		cond2.setNombre("chi");
		tmio1ConductoreDao.save(cond2);
		List<Object[]> consulta= tmio1ConductoreDao.consultaAdicional(new Date(119, 10, 25));
		assertEquals(((Tmio1Conductore)consulta.get(0)[0]).getCedula(),"123" );
		assertTrue(consulta.get(0)[1].toString().equals("2") );
		
		assertEquals(((Tmio1Conductore)consulta.get(1)[0]).getCedula(),"124" );
		assertTrue(consulta.get(1)[1].toString().equals("0") );
		
	}

}
