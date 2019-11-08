package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

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
public class Tmio1ServiceDaoTest {
	
	@Autowired
	private Tmio1ServicioDao tmio1ServicioDao;
	@Autowired
	private Tmio1BusDao tmio1BusDao;
	@Autowired
	private Tmio1ConductoreDao tmio1ConductoreDao;
	@Autowired
	private Tmio1RutaDao tmio1RutaDao;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(tmio1ServicioDao);
		
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
		
		tmio1ServicioDao.save(s1);
		tmio1ServicioDao.save(s2);

		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(tmio1ServicioDao);
		
		
		
	}


}
