package co.edu.icesi.ci.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.repositories.BusesRepository;

@Service
public class ServicioBusImp implements ServicioBus {
	@Autowired
	private BusesRepository repositorio;
	@Override
	public Tmio1Bus guardarBus(Tmio1Bus bus) throws Exception {
		if(bus == null) {
			throw new Exception("No se pueden agregar buses nulos");
		}else if(bus.getCapacidad().compareTo(new BigDecimal(0))<=0) {
			throw new Exception("Bus con capacidad no valida");
		}else if(!(bus.getTipo().equals("A")||bus.getTipo().equals("T")||bus.getTipo().equals("P"))) {
			
			throw new Exception("Bus de tipo no valido");
		}else if(repositorio.findByPlaca(bus.getPlaca()).isPresent()) {
			
			throw new Exception("ya existe un bus con esa placa");
		}
		return repositorio.save(bus);
	}

	@Override
	public Tmio1Bus removerBus(Tmio1Bus bus) throws Exception {
		repositorio.delete(bus);
		return bus ;
	}

	@Override
	public Tmio1Bus actualizarBus(Tmio1Bus bus) throws Exception {
		
		return repositorio.save(bus);
	}

	@Override
	public Optional<Tmio1Bus> consultarBus(Integer id) throws Exception {
		
		return repositorio.findById(id);
	}

	@Override
	public Iterable<Tmio1Bus> findAll() {
		
		return repositorio.findAll();
	}

	@Override
	public Iterable<Tmio1Bus> consultarBus(String placa) throws Exception {
		Optional<Tmio1Bus> bus= repositorio.findByPlaca(placa);
		if(bus.isPresent()) {
			ArrayList<Tmio1Bus> lista= new ArrayList<>();
			lista.add(bus.get());
			return lista;
		}else {
			return null;
		}
	}

}
