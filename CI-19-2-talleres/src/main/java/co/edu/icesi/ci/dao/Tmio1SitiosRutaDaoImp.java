package co.edu.icesi.ci.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;

@Repository
public class Tmio1SitiosRutaDaoImp implements Tmio1SitiosRutaDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1SitiosRuta entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1SitiosRuta entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1SitiosRuta entity) {
		entityManager.remove(entity);
	}

	@Override
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id) {
		return entityManager.find(Tmio1SitiosRuta.class, id);
	}
	
//	@Override
//	public Tmio1SitiosRuta findById(Integer id) {
//		return entityManager.find(Tmio1SitiosRuta.class, id);
//	}

	@Override
	public ArrayList<Tmio1SitiosRuta> findAll() {
		String jpql = "Select r From Tmio1SitiosRuta r";
		return (ArrayList<Tmio1SitiosRuta>) entityManager.createQuery(jpql).getResultList();
	}

}
