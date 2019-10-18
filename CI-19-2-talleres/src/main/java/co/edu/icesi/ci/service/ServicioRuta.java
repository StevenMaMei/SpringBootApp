package co.edu.icesi.ci.service;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface ServicioRuta {
	public Tmio1Ruta guardarRuta(Tmio1Ruta c) throws Exception;
	public Tmio1Ruta removerRuta(Tmio1Ruta ruta) throws Exception;
	public Tmio1Ruta actualizarRuta(Tmio1Ruta Ruta) throws Exception;
	public Optional<Tmio1Ruta> consultarRuta(Integer id) throws Exception;
	public Iterable<Tmio1Ruta> findAll();
	public Iterable<Tmio1Ruta> findByDescripcion(String des);
}
