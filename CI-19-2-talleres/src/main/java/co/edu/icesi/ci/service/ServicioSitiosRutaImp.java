package co.edu.icesi.ci.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.dao.Tmio1RutaDao;
import co.edu.icesi.ci.dao.Tmio1SitioDao;
import co.edu.icesi.ci.dao.Tmio1SitiosRutaDao;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaWrapper;

@Service
public class ServicioSitiosRutaImp implements ServicioSitiosRuta {

	@Autowired
	private Tmio1SitiosRutaDao repos;

	@Autowired
	private Tmio1SitioDao repoSitios;

	@Autowired
	private Tmio1RutaDao repoRutas;

//	@Override
//	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public Tmio1SitiosRuta guardarSitioRuta(Tmio1SitiosRutaPK sitioRuta) throws Exception {
//		if (sitioRuta != null && repoSitios.findById(sitioRuta.getIdSitio()) != null
//				&& repoRutas.findById(sitioRuta.getIdRuta()) != null) {
//			Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
//			sr.setShit("SHIT");
//			sr.setId(sitioRuta);
//			sr.setTmio1Ruta1(repoRutas.findById(sitioRuta.getIdRuta()));
//			sr.setTmio1Sitio1(repoSitios.findById(sitioRuta.getIdSitio()));
//			repos.save(sr);
//			System.out.println(sr.getTmio1Ruta1().getDescripcion());
//			System.out.println(sr.getTmio1Sitio1().getNombre());
//			System.out.println(repos.findAll().get(0).getTmio1Ruta1().getDescripcion());
//			return sr;
//		} else {
//			throw new RuntimeException();
//		}
//	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1SitiosRuta guardarSitioRuta(Tmio1SitiosRutaWrapper wrapper) throws Exception {
		long idSitio = wrapper.getIdSitio();
		Integer idRuta = wrapper.getIdRuta();
		Tmio1Sitio sitio = repoSitios.findById(idSitio);
		Tmio1Ruta ruta = repoRutas.findById(idRuta.intValue());
		
		if(sitio == null)
			throw new Exception("No existe el sitio.");
		if(ruta == null)
			throw new Exception("No existe la ruta.");
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		Tmio1SitiosRuta sitioRuta = new Tmio1SitiosRuta();
		id.setIdSitio(idSitio);
		id.setIdRuta(idRuta);
		
		sitioRuta.setId(id);
		sitioRuta.setTmio1Sitio1(sitio);
		sitioRuta.setTmio1Ruta1(ruta);
		
		repos.save(sitioRuta);
		
		return sitioRuta;
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tmio1SitiosRuta eliminarSitioRuta(Tmio1SitiosRuta sitioRuta) throws Exception {
		repos.delete(sitioRuta);
		return sitioRuta;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarSitioRuta(Tmio1SitiosRutaWrapper wrapper) throws Exception {
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdSitio(wrapper.getIdSitioViejo());
		id.setIdRuta(wrapper.getIdRutaViejo());
		
//		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
//		id.setIdSitio(wrapper.getIdSitioViejo());
//		id.setIdRuta(wrapper.getIdRutaViejo());
		
		
		//TODO FIX THIS
		Tmio1SitiosRuta r = repos.findById(id);
		
		r.setTmio1Sitio1(repoSitios.findById(wrapper.getIdSitio()));
		r.setTmio1Ruta1(repoRutas.findById(wrapper.getIdRuta()));
		Tmio1SitiosRutaPK idd = new Tmio1SitiosRutaPK();
		idd.setIdSitio(wrapper.getIdSitio());
		idd.setIdRuta(wrapper.getIdRuta());
		r.setId(idd);
		
	}

	@Override
	public Tmio1SitiosRuta consultarSitioRuta(Tmio1SitiosRutaPK id) throws Exception {
		return repos.findById(id);
	}

//	@Override
//	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public Tmio1SitiosRuta consultarSitioRuta(Integer id) throws Exception {
//		return repos.findById(id);
//	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ArrayList<Tmio1SitiosRuta> findAll() {
		return repos.findAll();
	}

}
