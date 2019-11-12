package co.edu.icesi.ci.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.dao.Tmio1RutaDao;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;

@Service
public class ServicioRutaImp implements ServicioRuta {

	@Autowired
	private Tmio1RutaDao repositorio;
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Ruta guardarRuta(Tmio1Ruta c) throws Exception {
		if(c == null) {
			throw new Exception( "No se pueden agregar rutas nulas");
		}else if(c.getHoraFin().compareTo(c.getHoraInicio())<0) {
			throw new Exception("La hora de inicio y fin no son consistentes");
		}else if(c.getDiaFin().compareTo(c.getDiaInicio())<0) {
			throw new Exception("El dia de inicio y fin no son consistentes");
		}else if(c.getDiaFin().compareTo(new BigDecimal(7))>0) {
			throw new Exception("El fin no es valido");
		}else if(!((c.getDiaInicio().equals(new BigDecimal(1))&&c.getDiaFin().equals(new BigDecimal(5)))
				||(c.getDiaInicio().equals(new BigDecimal(1))&&c.getDiaFin().equals(new BigDecimal(6)))
				|| (c.getDiaInicio().equals(new BigDecimal(6))&&c.getDiaFin().equals(new BigDecimal(7)))
				|| (c.getDiaInicio().equals(new BigDecimal(6))&&c.getDiaFin().equals(new BigDecimal(6)))
				|| (c.getDiaInicio().equals(new BigDecimal(7))&&c.getDiaFin().equals(new BigDecimal(7))))){
			throw new Exception("Ruta con rango de horario no valido");
		}
		repositorio.save(c);
		return c;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Ruta removerRuta(Tmio1Ruta ruta) throws Exception {
		repositorio.delete(ruta);
		return ruta;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Ruta actualizarRuta(Tmio1Ruta Ruta) throws Exception {
		repositorio.update(Ruta);
		return Ruta;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Ruta consultarRuta(Integer id) throws Exception {
		
		return repositorio.findById(id);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<Tmio1Ruta> findAll() {
		
		return repositorio.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<Tmio1Ruta> findByDescripcion(String des) {
		return repositorio.findByDescripcion(des);
	}

}
