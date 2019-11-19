package co.edu.icesi.ci.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

@Repository
public class Tmio1SitioDaoImp implements Tmio1SitioDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Sitio sitio) {
		entityManager.persist(sitio);
	}

	@Override
	public void update(Tmio1Sitio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Sitio entity) {
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Sitio findById(long id) {
		String jpql= "Select b"
				+ " from Tmio1Sitio b "
				+ "where b.id = :idd ";
		List<Tmio1Sitio> consulta = new ArrayList<Tmio1Sitio>();
		try {
			consulta.add(entityManager.find(Tmio1Sitio.class, id));
			
			
		}catch(Exception e) {
			
		}
		if(consulta.size()==0) {
			return null;
		}else {
			return consulta.get(0);
		}
	}

	@Override
	public ArrayList<Tmio1Sitio> findAll() {
		String jpql= "Select b"
				+ " from Tmio1Sitio b ";
		List<Tmio1Sitio> consulta = entityManager.createQuery(jpql).getResultList();
		return (ArrayList<Tmio1Sitio>) consulta;
	}

}
