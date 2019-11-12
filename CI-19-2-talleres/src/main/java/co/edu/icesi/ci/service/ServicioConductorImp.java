package co.edu.icesi.ci.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.dao.Tmio1ConductoreDao;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;

@Service
public class ServicioConductorImp implements ServicioConductor{
	@Autowired
	private Tmio1ConductoreDao repositorio;
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Conductore guardarConductor(Tmio1Conductore c) throws Exception {
		if(c== null) {
			throw new Exception("No se pueden agregar conductores nulos");
		}else if(c.getFechaContratacion().compareTo(c.getFechaNacimiento())<=0) {
			
			throw new Exception("La fecha de contratacion y nacimiento del conductor no son consistentes");
		}else if(repositorio.findByCedula(c.getCedula())!= null) {
			throw new Exception("ya existe conductor con esa cedula");
		}
		repositorio.save(c);
		return c;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Conductore removerConductor(Tmio1Conductore conductor) throws Exception {
		repositorio.delete(conductor);
		return conductor;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Conductore actualizarConductor(Tmio1Conductore conductor) throws Exception {
		// TODO Auto-generated method stub
		repositorio.update(conductor);
		return conductor ;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Conductore consultarConductor(String cedula) throws Exception {
		return repositorio.findByCedula(cedula);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<Tmio1Conductore> findAll() {
		
		return repositorio.findAll();
	}

}
