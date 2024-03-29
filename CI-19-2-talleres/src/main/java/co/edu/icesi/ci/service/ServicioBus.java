package co.edu.icesi.ci.service;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface ServicioBus {
	public Tmio1Bus guardarBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus removerBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus actualizarBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus consultarBus(Integer id) throws Exception;
	public ArrayList<Tmio1Bus> consultarBus(String placa) throws Exception;
	public ArrayList<Tmio1Bus> findAll();
}
