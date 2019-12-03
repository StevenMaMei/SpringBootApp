package co.edu.icesi.ci.service;

import java.util.ArrayList;

import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaWrapper;

public interface ServicioSitiosRuta {
	
//	public Tmio1SitiosRuta guardarSitioRuta(Tmio1SitiosRutaPK sitioRuta) throws Exception;
//	public Tmio1SitiosRuta eliminarSitioRuta(Tmio1SitiosRuta sitioRuta) throws Exception;
	public void actualizarSitioRuta(Tmio1SitiosRutaWrapper wrapper) throws Exception;
	public Tmio1SitiosRuta consultarSitioRuta(Tmio1SitiosRutaPK id) throws Exception;
//	public Tmio1SitiosRuta consultarSitioRuta(Integer id) throws Exception;
	public ArrayList<Tmio1SitiosRuta> findAll();
	public Tmio1SitiosRuta guardarSitioRuta(Tmio1SitiosRutaWrapper wrapper) throws Exception;
	public Tmio1SitiosRuta eliminarSitioRuta(Tmio1SitiosRutaWrapper wrapper) throws Exception;
}
