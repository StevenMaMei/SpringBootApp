package co.edu.icesi.ci.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.dao.Tmio1SitioDao;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
@Service
public class ServicioSitioImp implements ServicioSitio {
	@Autowired
	private Tmio1SitioDao repos;
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Sitio guardarSitio(Tmio1Sitio Sitio) throws Exception {
		repos.save(Sitio);
		return Sitio;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Sitio removerSitio(Tmio1Sitio Sitio) throws Exception {
		repos.delete(Sitio);
		return Sitio;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Sitio actualizarSitio(Tmio1Sitio Sitio) throws Exception {
		repos.update(Sitio);
		return Sitio;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1Sitio consultarSitio(long id) throws Exception {
		Tmio1Sitio sitio= repos.findById(id);
		return sitio;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ArrayList<Tmio1Sitio> findAll() {
		return repos.findAll();
	}



}
