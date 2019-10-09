package co.edu.icesi.ci.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.repositories.BusesRepository;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;
import co.edu.icesi.ci.talleres.repositories.ServiciosRepository;

import lombok.Data;
@Service
@Data
public class ServicioServicioImp implements ServicioServicio {
	@Autowired
	private BusesRepository repositorioBus;
	
	@Autowired
	private RutasRepository repositorioRuta;
	
	@Autowired
	private ConductoresRepository repositorioConductor;
	
	@Autowired
	private ServiciosRepository repositorioServicio;
	@Override
	public Tmio1Servicio guardarServicio(Integer busId, String cedula, Integer rutaId, Date fechaIni, Date fechaFin) throws Exception {
		Optional<Tmio1Bus> bus= repositorioBus.findById(busId);
		Optional<Tmio1Conductore> conductor= repositorioConductor.findById(cedula);
		Optional<Tmio1Ruta> ruta = repositorioRuta.findById(rutaId);
		if(!bus.isPresent() || !conductor.isPresent() || !ruta.isPresent()) {
			if(!bus.isPresent()) {
				throw new Exception( "No existe el bus");
			}else if(!conductor.isPresent()) {
				throw new Exception("No existe el conductor");
			}else if(!ruta.isPresent()) {
				throw new Exception("No existe la ruta");
			}
		}else if( fechaIni== null || fechaIni==null) {
			
			throw new Exception("Las fechas no pueden ser nulas");
		}else if( fechaIni.compareTo(fechaFin)>0) {
			
			throw new Exception("La fecha de inicio y fin del servicio no son consistentes");
		}else if( fechaIni.compareTo(conductor.get().getFechaContratacion())<0) {
			throw new Exception("La fecha de inicio del servicio es anterior a la fecha de contratacion");
		}
		boolean conflictoConConductor =false;
		Tmio1Servicio s= new Tmio1Servicio();
		s.setTmio1Bus(bus.get());
		s.setTmio1Conductore(conductor.get());
		s.setTmio1Ruta(ruta.get());
		
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(cedula);
		pk.setIdBus(busId);
		pk.setIdRuta(rutaId);
		pk.setFechaFin(fechaFin);
		pk.setFechaInicio(fechaIni);
		s.setId(pk);
		return repositorioServicio.save(s);
	}

	@Override
	public Tmio1Servicio removerServicio(Tmio1Servicio servicio) throws Exception {
		repositorioServicio.delete(servicio);
		return servicio;
	}

	@Override
	public Tmio1Servicio actualizarServicio( Tmio1Servicio Servicio) throws Exception {
		// TODO hacer verificaciones
		return repositorioServicio.save(Servicio);
	}

	@Override
	public Optional<Tmio1Servicio> consultarServicio(Integer busId, String cedula, Integer rutaId, Date fechaIni, Date fechaFin) throws Exception {
		
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(cedula);
		pk.setIdBus(busId);
		pk.setIdRuta(rutaId);
		pk.setFechaFin(fechaFin);
		pk.setFechaInicio(fechaIni);
		
		return repositorioServicio.findById(pk);
	}

}
