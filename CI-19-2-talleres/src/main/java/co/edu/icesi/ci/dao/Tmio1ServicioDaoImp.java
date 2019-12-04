package co.edu.icesi.ci.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
@Repository
public class Tmio1ServicioDaoImp implements Tmio1ServicioDao {
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
		String jpql = "Select a from Tmio1Servicio a "
				+ "where a.id.idRuta = :idR AND a.id.cedulaConductor = :ced AND "
				+ "a.id.idBus = :idB ";
		
		ArrayList<Tmio1Servicio> con = (ArrayList<Tmio1Servicio>) entityManager.createQuery(jpql).setParameter("idR", pk.getIdRuta()).setParameter("ced", pk.getCedulaConductor()).setParameter("idB", pk.getIdBus())
				.getResultList();
		Tmio1Servicio toReturn = null;
		for(Tmio1Servicio s : con) {
			if(s.getId().getFechaInicio().compareTo(pk.getFechaInicio()) >= 0  && s.getId().getFechaFin().compareTo(pk.getFechaFin()) <=0 ) {
				toReturn = s;
				break;
			}
		}
		if(con.size() == 0)
			return 	null;
		else
			return con.get(0);
			
		
	}

	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql = "Select a from Tmio1Servicio a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	

}
