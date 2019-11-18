package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;


public interface Tmio1BusDao {
	public void save(Tmio1Bus bus);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	public Tmio1Bus findByPlaca(String placa);
	public Tmio1Bus findById(Integer id);
	public ArrayList<Tmio1Bus> findByMarca(String marca);
	public ArrayList<Tmio1Bus> findByModelo(BigDecimal modelo);
	public ArrayList<Tmio1Bus> consultaAdicional(Date fecha);
	public ArrayList<Tmio1Bus> findAll();
	
	
}
