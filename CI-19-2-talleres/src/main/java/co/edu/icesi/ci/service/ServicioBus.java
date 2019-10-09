package co.edu.icesi.ci.service;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface ServicioBus {
	public Tmio1Bus guardarBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus removerBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus actualizarBus(Tmio1Bus bus) throws Exception;
	public Optional<Tmio1Bus> consultarBus(Integer id) throws Exception;
}
