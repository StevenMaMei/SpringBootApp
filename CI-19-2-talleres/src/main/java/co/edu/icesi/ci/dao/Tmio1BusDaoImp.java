package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
@Repository
public class Tmio1BusDaoImp implements Tmio1BusDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(Tmio1Bus bus) {
		entityManager.persist(bus);
		
	}

	@Override
	public void update(Tmio1Bus entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Tmio1Bus entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Tmio1Bus findByPlaca(String placa) {
		String jpql= "Select b"
				+ " from Tmio1Bus b "
				+ "where b.placa ="+"'"+placa+"'";
		
		List<Tmio1Bus> consulta = entityManager.createQuery(jpql).getResultList();
		if(consulta.size() == 0) {
			return null;
			
		}else {
			return consulta.get(0);
		}
	}

	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		String jpql= "Select b"
				+ " from Tmio1Bus b "
				+ "where b.marca ="+"'"+marca+"'";
		
		List<Tmio1Bus> consulta = entityManager.createQuery(jpql).getResultList();

		return consulta;
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		String jpql= "Select b"
				+ " from Tmio1Bus b "
				+ "where b.placa ="+"'"+modelo.toString()+"'";
		
		List<Tmio1Bus> consulta = entityManager.createQuery(jpql).getResultList();

		return consulta;
	}

	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> consultaAdicional(Date fecha) {
		String jpql = "Select  t "
				+ "From Tmio1Bus t "
				+ "Where 1< (select count(k.id.idBus) From Tmio1Servicio k Where k.id.idBus = t.id  AND k.id.fechaInicio<= :thedate AND k.id.fechaFin >= :thedate  )";
		return entityManager.createQuery(jpql).setParameter("thedate", fecha).getResultList();
		//
		//
	}

}
