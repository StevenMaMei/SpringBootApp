package co.edu.icesi.ci.service;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface ServicioConductor {
	public Tmio1Conductore guardarConductor(Tmio1Conductore c) throws Exception;
	public Tmio1Conductore removerConductor(Tmio1Conductore c)throws Exception;
	public Tmio1Conductore actualizarConductor(Tmio1Conductore conductor)throws Exception;
	public Optional<Tmio1Conductore> consultarConductor(String id)throws Exception;
	
	
}
