package co.edu.icesi.ci.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
@Repository
public class Tmio1ConductoreImp implements Tmio1ConductoreDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Conductore Conductore) {
		entityManager.persist(Conductore);
	}

	@Override
	public void update(Tmio1Conductore entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Tmio1Conductore entity) {
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Conductore findByCedula(String cedula) {
		String jpql= "Select t "
				+ "from Tmio1Conductore t "
				+ "where t.cedula="+"'"+cedula+"'";
		
		return (Tmio1Conductore)entityManager.createQuery(jpql).getSingleResult();
	}

	@Override
	public List<Tmio1Conductore> findByNombre(String nombre) {
		String jpql= "Select t "
				+ "from Tmio1Conductore t "
				+ "where t.nombre="+"'"+nombre+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByApellidos(String apellidos) {
		String jpql= "Select t "
				+ "from Tmio1Conductore t "
				+ "where t.apellidos="+"'"+apellidos+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql = "Select a from Tmio1Conductore a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	@Override
	public List<Object[]> consultaAdicional(Date fecha) {
		String jpql= "Select DISTINCT t, "
				+ "(Select COUNT(*) From Tmio1Servicio k Where k.id.fechaInicio <= :fechita  AND k.id.fechaFin >= :fechita AND k.id.cedulaConductor = t.cedula)AS cant "
				+ "from Tmio1Conductore t "
				+ "ORDER BY t.fechaNacimiento ";
				
		return 	entityManager.createQuery(jpql).setParameter("fechita", fecha).getResultList();
	}

	

}
