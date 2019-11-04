package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

public interface Tmio1ServicioDao {

	public void save(Tmio1Servicio Servicio);
	public void update(Tmio1Servicio entity);
	public void delete(Tmio1Servicio entity);
	public Tmio1Servicio findById(Tmio1ServicioPK pk);
	public List<Tmio1Servicio> findAll();
}
