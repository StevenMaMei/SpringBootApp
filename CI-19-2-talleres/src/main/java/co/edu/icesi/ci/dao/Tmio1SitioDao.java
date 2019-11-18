package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

public interface Tmio1SitioDao {
	public void save(Tmio1Sitio sitio);
	public void update(Tmio1Sitio entity);
	public void delete(Tmio1Sitio entity);
	public Tmio1Sitio findById(long id);
	public ArrayList<Tmio1Sitio> findAll();
}
