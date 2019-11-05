package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface Tmio1RutaDao {

	public void save(Tmio1Ruta Ruta);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	public Tmio1Ruta findById(int id);
	public List<Tmio1Ruta> findByRangoHoras(BigDecimal horaInicio, BigDecimal horaFin);
	public List<Tmio1Ruta> findByRangoFecha(Date fechaInicio, Date fechaFin);
	public List<Tmio1Ruta> consultaAdicional(Date fecha);
	public List<Tmio1Ruta> findAll();
}
