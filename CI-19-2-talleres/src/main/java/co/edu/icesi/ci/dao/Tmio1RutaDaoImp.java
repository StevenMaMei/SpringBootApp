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
	public Tmio1Ruta findById(Integer id) {
		return entityManager.find(Tmio1Ruta.class, id);
	}

	@Override
	public List<Tmio1Ruta> findByRangoHoras(BigDecimal horaInicio, BigDecimal horaFin) {
		String jpql= "Select t"
				+ "from Tmio1Ruta t"
				+ "where t.horaInicio>="+horaInicio+"AND t.horaFin<="+horaFin;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByRangoFecha(Date fechaInicio, Date fechaFin) {
		String jpql= "Select t"
				+ "from Tmio1Ruta t INNER JOIN Tmio1Servicio k ON t.id=k.idRuta"
				+ "where DATEDIFF(k.fechaInicio,"+fechaInicio+") >=0 AND"+"DATEDIFF(k.fechaFin, "+fechaFin+")<=0";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findAll() {
		
		String jpql = "Select a from TMio1Ruta a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> consultaAdicional(Date fecha) {
		String jpql= "Select t"
				+ "from Tmio1Ruta t INNER JOIN Tmio1Servicio k ON t.id=k.idRuta"
				+ "where DATEDIFF(k.fechaInicio,"+fecha+") <=0 "
						+ "AND"+"DATEDIFF(k.fechaFin, "+fecha+")>=0 "
						+ "AND (Select Count(*) From tmio1Sservicio k1 Where k1.idRuta= t.id) <= 10 ";
		return entityManager.createQuery(jpql).getResultList();
		
	}

	

}
