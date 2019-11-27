package co.edu.icesi.ci.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.dao.Tmio1RutaDao;
import co.edu.icesi.ci.dao.Tmio1SitioDao;
import co.edu.icesi.ci.dao.Tmio1SitiosRutaDao;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;

@Service
public class ServicioSitiosRutaImp implements ServicioSitiosRuta {

	@Autowired
	private Tmio1SitiosRutaDao repos;

	@Autowired
	private Tmio1SitioDao repoSitios;

	@Autowired
	private Tmio1RutaDao repoRutas;

	@Override
	public Tmio1SitiosRuta guardarSitioRuta(Tmio1SitiosRutaPK sitioRuta) throws Exception {
		if (sitioRuta != null && repoSitios.findById(sitioRuta.getIdSitio()) != null
				&& repoRutas.findById(sitioRuta.getIdRuta()) != null) {
			Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
			sr.setId(sitioRuta);
			sr.setTmio1Ruta1(repoRutas.findById(sitioRuta.getIdRuta()));
			sr.setTmio1Sitio1(repoSitios.findById(sitioRuta.getIdSitio()));
			repos.save(sr);
			return sr;
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Tmio1SitiosRuta eliminarSitioRuta(Tmio1SitiosRuta sitioRuta) throws Exception {
		repos.delete(sitioRuta);
		return sitioRuta;
	}

	@Override
	public Tmio1SitiosRuta actualizarSitioRuta(Tmio1SitiosRuta sitioRuta) throws Exception {
		repos.update(sitioRuta);
		return sitioRuta;
	}

	@Override
	public Tmio1SitiosRuta consultarSitioRuta(Tmio1SitiosRutaPK id) throws Exception {
		return repos.findById(id);
	}

	@Override
	public ArrayList<Tmio1SitiosRuta> findAll() {
		return repos.findAll();
	}

}
