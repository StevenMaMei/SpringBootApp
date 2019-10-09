package co.edu.icesi.ci.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;

@Service
public class ServicioConductorImp implements ServicioConductor{
	@Autowired
	private ConductoresRepository repositorio;
	@Override
	public Tmio1Conductore guardarConductor(Tmio1Conductore c) throws Exception {
		if(c== null) {
			throw new Exception("No se pueden agregar conductores nulos");
		}else if(c.getFechaContratacion().compareTo(c.getFechaNacimiento())<=0) {
			
			throw new Exception("La fecha de contratacion y nacimiento del conductor no son consistentes");
		}else if(repositorio.findById(c.getCedula()).isPresent()) {
			throw new Exception("ya existe conductor con esa cedula");
		}
		return repositorio.save(c);
	}

	@Override
	public Tmio1Conductore removerConductor(Tmio1Conductore conductor) throws Exception {
		repositorio.delete(conductor);
		return conductor;
	}

	@Override
	public Tmio1Conductore actualizarConductor(Tmio1Conductore conductor) throws Exception {
		// TODO Auto-generated method stub
		return repositorio.save(conductor);
	}

	@Override
	public Optional<Tmio1Conductore> consultarConductor(String cedula) throws Exception {
		return repositorio.findById(cedula);
	}

}
