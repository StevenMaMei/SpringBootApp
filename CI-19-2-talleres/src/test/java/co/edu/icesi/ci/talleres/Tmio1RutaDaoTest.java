package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class Tmio1RutaDaoTest {
	
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

		assertNotNull(tmio1RutaDao);
		Tmio1Ruta ru= new Tmio1Ruta();
		ru.setActiva("a");
		ru.setDescripcion("as");
		ru.setDiaFin(new BigDecimal(6));
		ru.setDiaInicio(new BigDecimal(1));
		ru.setHoraFin(new BigDecimal("2"));
		ru.setHoraInicio(new BigDecimal("1"));
		ru.setNumero("1");
		
		tmio1RutaDao.save(ru);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(tmio1RutaDao);
		
		Tmio1Ruta ru= new Tmio1Ruta();
		ru.setActiva("a");
		ru.setDescripcion("as");
		ru.setDiaFin(new BigDecimal(6));
		ru.setDiaInicio(new BigDecimal(1));
		ru.setHoraFin(new BigDecimal("2"));
		ru.setHoraInicio(new BigDecimal("1"));
		ru.setNumero("1");
		
		tmio1RutaDao.save(ru);
		ru.setDescripcion("ase");
		tmio1RutaDao.update(ru);
		Tmio1Ruta ruta=tmio1RutaDao.findById(ru.getId());
		assertNotNull(ruta);
		assertEquals(ruta.getDescripcion(), "ase");
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(tmio1RutaDao);
		Tmio1Ruta ru= new Tmio1Ruta();
		ru.setActiva("a");
		ru.setDescripcion("as");
		ru.setDiaFin(new BigDecimal(6));
		ru.setDiaInicio(new BigDecimal(1));
		ru.setHoraFin(new BigDecimal("2"));
		ru.setHoraInicio(new BigDecimal("1"));
		ru.setNumero("1");
		tmio1RutaDao.save(ru);
		Tmio1Ruta ruta = tmio1RutaDao.findById(ru.getId());
		assertNotNull(ruta);
		tmio1RutaDao.delete(ruta);
		ruta= tmio1RutaDao.findById(1);
		assertNull(ruta);
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByIdTest() {

		assertNotNull(tmio1RutaDao);
		Tmio1Ruta ru= new Tmio1Ruta();
		ru.setActiva("a");
		ru.setDescripcion("as");
		ru.setDiaFin(new BigDecimal(6));
		ru.setDiaInicio(new BigDecimal(1));
		ru.setHoraFin(new BigDecimal("2"));
		ru.setHoraInicio(new BigDecimal("1"));
		ru.setNumero("1");
		tmio1RutaDao.save(ru);
		Tmio1Ruta ruta = tmio1RutaDao.findById(ru.getId());
		
		assertNotNull(ruta);
		assertEquals(ruta.getDescripcion(), "as");
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByRangoHorasTest() {

		assertNotNull(tmio1RutaDao);
		
		Tmio1Ruta ru1= new Tmio1Ruta();
		ru1.setActiva("a");
		ru1.setDescripcion("a");
		ru1.setDiaFin(new BigDecimal(6));
		ru1.setDiaInicio(new BigDecimal(1));
		ru1.setHoraFin(new BigDecimal("2"));
		ru1.setHoraInicio(new BigDecimal("1"));
		ru1.setNumero("1");
		
		Tmio1Ruta ru2= new Tmio1Ruta();
		ru2.setActiva("a");
		ru2.setDescripcion("b");
		ru2.setDiaFin(new BigDecimal(5));
		ru2.setDiaInicio(new BigDecimal(1));
		ru2.setHoraFin(new BigDecimal("3"));
		ru2.setHoraInicio(new BigDecimal("1"));
		ru2.setNumero("1");
		
		tmio1RutaDao.save(ru1);
		tmio1RutaDao.save(ru2);
		
		List<Tmio1Ruta> rutas = tmio1RutaDao.findByRangoHoras(new BigDecimal(1), new BigDecimal(2));
		assertEquals(rutas.size(), 1);
		assertEquals(rutas.get(0).getDescripcion(), "a");
		
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByRangoFechasTest() {

		assertNotNull(tmio1RutaDao);
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

		tmio1RutaDao.save(ruta2);
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
		pk2.setIdRuta(ruta2.getId());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setTmio1Bus(bus);
		s2.setId(pk2);
		s2.setTmio1Conductore(cond);
		s2.setTmio1Ruta(ruta2);
		
		tmio1Servicio.save(s1);
		tmio1Servicio.save(s2);
		
		List<Tmio1Ruta> rutas = tmio1RutaDao.findByRangoFecha(new Date(119, 10, 25), new Date(119,10,31));
		assertEquals(rutas.size(), 1);
		assertEquals(rutas.get(0).getDescripcion(), "a");
		
		rutas = tmio1RutaDao.findByRangoFecha(new Date(119, 10, 20), new Date(119,10,31));
		assertEquals(rutas.size(), 1);
		assertEquals(rutas.get(0).getDescripcion(), "b");
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void consultaAdicionalTest() {

		assertNotNull(tmio1RutaDao);
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

		tmio1RutaDao.save(ruta2);
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
		pk2.setIdRuta(ruta2.getId());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setTmio1Bus(bus);
		s2.setId(pk2);
		s2.setTmio1Conductore(cond);
		s2.setTmio1Ruta(ruta2);
		
		tmio1Servicio.save(s1);
		tmio1Servicio.save(s2);
		
		List<Tmio1Ruta> rutas = tmio1RutaDao.consultaAdicional(new Date(119, 10, 25));
		assertEquals(rutas.size(), 2);
		
		
	}





}
