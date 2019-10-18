package co.edu.icesi.ci.service;

import java.util.Date;
import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;



public interface ServicioServicio {

	public Tmio1Servicio guardarServicio(Integer busId, String cedula, Integer ruta, Date fechaIni, Date fechaFin) throws Exception;
	
	public Tmio1Servicio removerServicio(Tmio1Servicio servicio)throws Exception;
	public Tmio1Servicio actualizarServicio(Tmio1Servicio ServicioMock)throws Exception;
	public Optional<Tmio1Servicio> consultarServicio(Integer busId, String cedula, Integer rutaId, Date fechaIni, Date fechaFin)throws Exception;
	public Iterable<Tmio1Servicio> findAll();
	public Iterable<Tmio1Servicio> findByDate(Date date);
}
