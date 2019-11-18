package co.edu.icesi.ci.service;

import java.util.ArrayList;

import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

public interface ServicioSitio {

	public Tmio1Sitio guardarSitio(Tmio1Sitio Sitio) throws Exception;
	public Tmio1Sitio removerSitio(Tmio1Sitio Sitio) throws Exception;
	public Tmio1Sitio actualizarSitio(Tmio1Sitio Sitio) throws Exception;
	public Tmio1Sitio consultarSitio(long id) throws Exception;
	public ArrayList<Tmio1Sitio> findAll();
}
