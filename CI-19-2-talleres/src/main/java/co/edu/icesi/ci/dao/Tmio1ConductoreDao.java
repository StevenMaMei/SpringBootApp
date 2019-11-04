package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface Tmio1ConductoreDao {

	public void save(Tmio1Conductore Conductore);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public Tmio1Conductore findByCedula(String cedula);
	public List<Tmio1Conductore> findByNombre(String nombre);
	public List<Tmio1Conductore> findByApellidos(String apellidos);
	public List<Tmio1Conductore> findAll();
	public List<Object[]> consultaAdicional(Date fecha);
}
