package co.edu.icesi.ci.dao;

import java.util.ArrayList;

import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;

public interface Tmio1SitiosRutaDao {
	public void save(Tmio1SitiosRuta entity);
	public void update(Tmio1SitiosRuta entity);
	public void delete(Tmio1SitiosRuta entity);
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id);
	public ArrayList<Tmio1SitiosRuta> findAll();
 
}
