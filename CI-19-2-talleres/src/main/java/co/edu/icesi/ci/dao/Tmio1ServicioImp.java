package co.edu.icesi.ci.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
@Repository
public class Tmio1ServicioImp implements Tmio1ServicioDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(Tmio1Servicio Servicio) {
		entityManager.persist(Servicio);
	}

	@Override
	public void update(Tmio1Servicio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Servicio entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK pk) {
		return entityManager.find(Tmio1Servicio.class, pk);
		
	}

	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql = "Select a from Tmio1Servicio a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	

}
