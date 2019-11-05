package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
@Repository
public class Tmio1RutaDaoImp implements Tmio1RutaDao{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(Tmio1Ruta Ruta) {
		entityManager.persist(Ruta);
	}

	@Override
	public void update(Tmio1Ruta entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Ruta entity) {
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Ruta findById(int id) {
		String jpql= "Select t "
				+ "from Tmio1Ruta t "
				+ "where t.id= :idd";
		Tmio1Ruta ruta= null;
		List<Tmio1Ruta> con= entityManager.createQuery(jpql).setParameter("idd", id).getResultList();
		if(con.size()>0) {
			ruta= con.get(0);
		}
		return ruta;
	}

	@Override
	public List<Tmio1Ruta> findByRangoHoras(BigDecimal horaInicio, BigDecimal horaFin) {
		String jpql= "Select t "
				+ "from Tmio1Ruta t "
				+ "where t.horaInicio = :h1 AND t.horaFin = :h2";
		return entityManager.createQuery(jpql).setParameter("h1", horaInicio).setParameter("h2",horaFin).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByRangoFecha(Date fechaInicio, Date fechaFin) {
		String jpql= "Select t "
				+ "from Tmio1Ruta t INNER JOIN Tmio1Servicio k ON t.id=k.id.idRuta "
				+ "where k.id.fechaInicio = :f1 AND k.id.fechaFin = :f2";
		return entityManager.createQuery(jpql).setParameter("f1", fechaInicio).setParameter("f2", fechaFin).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findAll() {
		
		String jpql = "Select a from Tmio1Ruta a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> consultaAdicional(Date fecha) {
		String jpql= "Select DISTINCT t "
				+ "from Tmio1Ruta t "
				+ "where (Select Count(k1.id.idRuta) From Tmio1Servicio k1 Where k1.id.idRuta= t.id AND k1.id.fechaInicio <= :f AND k1.id.fechaFin >= :f) <= 10 ";
		return entityManager.createQuery(jpql).setParameter("f", fecha).getResultList();
		
	}

	

}
