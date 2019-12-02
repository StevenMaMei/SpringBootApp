package co.edu.icesi.ci.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.delegate.Delegate;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaWrapper;

@Controller
public class SitiosRutaController {
	
	@Autowired
	private Delegate delegate;
	
	@GetMapping("/sitios-rutas/")
	public String indexSitiosRutas(Model model) {
		ArrayList<Tmio1SitiosRuta> x = delegate.getSitiosRutas();
//		model.addAttribute("sitiosrutas", delegate.getSitiosRutas());
		model.addAttribute("sitiosrutas", x);
		System.out.println(x.size());
		return "sitios-rutas/index";
	}
	
	@GetMapping("/sitios-rutas/add/")
	public String addSitioRuta(Model model) {
		model.addAttribute("rutas",delegate.getRutas());
		model.addAttribute("sitios",delegate.getSitios());
		model.addAttribute("tmio1SitiosRutaWrapper", new Tmio1SitiosRutaWrapper());
		return "sitios-rutas/add";
	}
	
	@PostMapping("/sitios-rutas/add")
	public String saveSitioRuta(@Valid Tmio1SitiosRutaWrapper tmio1SitiosRutaWrapper, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("rutas",delegate.getRutas());
				model.addAttribute("sitios",delegate.getSitios());
				model.addAttribute("tmio1SitiosRutaWrapper", new Tmio1SitiosRutaWrapper());
				return "sitios-rutas/add";
			}
			delegate.saveSitioRuta(tmio1SitiosRutaWrapper);
		}
		return "redirect:/sitios-rutas/";		
	}
	
//	@GetMapping("/sitios-rutas/edit/{hash}")
//	public String showUpdateSitioRuta(Model model, @PathVariable("hash") int hash) {
//		model.addAttribute("rutas",delegate.getRutas());
//		model.addAttribute("sitios",delegate.getSitios());
//		model.addAttribute("pk", new Tmio1SitiosRutaPK());
//		return "sitios-rutas/edit";
//	}
	
	
	@GetMapping("/sitios-rutas/edit/{sitio}/{ruta}")
	public String showUpdateSitioRuta(Model model, @PathVariable("sitio") long sitio, @PathVariable("ruta") int ruta) {
		Tmio1SitiosRutaWrapper wrapper = new Tmio1SitiosRutaWrapper();
		wrapper.setIdSitioViejo(sitio);
		wrapper.setIdRutaViejo(ruta);
		
		// TODO MAKE A REQUEST TO RETURN DATA FROM DATABASE WHEN LOOKING THIS UP
		// AL TRATAR DE CREAR PARECE DARSE UN ERROR PARTICULAR RESPECTO A LOS SITIOS???
		
		
		model.addAttribute("rutas",delegate.getRutas());
		model.addAttribute("sitios",delegate.getSitios());
		model.addAttribute("tmio1SitiosRutaWrapper", wrapper);
		return "sitios-rutas/edit";
	}
	
	@PostMapping("/sitios-rutas/edit")
	public String updateSitioRuta(@Valid Tmio1SitiosRutaWrapper tmio1SitiosRutaWrapper, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				Tmio1SitiosRutaWrapper wrapper = new Tmio1SitiosRutaWrapper();
				wrapper.setIdSitio(tmio1SitiosRutaWrapper.getIdSitio());
				wrapper.setIdRuta(tmio1SitiosRutaWrapper.getIdRuta());
				model.addAttribute("rutas",delegate.getRutas());
				model.addAttribute("sitios",delegate.getSitios());
				model.addAttribute("tmio1SitiosRutaWrapper", wrapper);
			}
		}
		return "redirect:/sitios-rutas/";
	}
	
	
	
	
	
	
	
//	@PostMapping("/sitios-rutas/edit/{hash}")
//	public String updateSitioRuta(@PathVariable("hash") int hash, @RequestParam(value = "action", required = true) String action, @Valid Tmio1SitiosRutaPK tmio1SitiosRutaPK, BindingResult bindingResult, Model model) {
//		if(!action.equals("Cancel")) {
//			if(bindingResult.hasErrors()) {
//				model.addAttribute("rutas",delegate.getRutas());
//				model.addAttribute("sitios",delegate.getSitios());
//				model.addAttribute("hash", new Tmio1SitiosRutaPK());
//				return "sitios-rutas/edit";
//			}
//			Tmio1SitiosRutaPK id = delegate.findSitiosRuta(hash).getId();
//			delegate.deleteSitiosRuta(id);
//			Tmio1SitiosRuta t = new Tmio1SitiosRuta();
//			t.setId(id);
//			t.setTmio1Sitio1(delegate.findSitio(id.getIdSitio()).get(0));
//			t.setTmio1Ruta1(delegate.findRutaByDescripcion());
//		}
//		return "redirect:/sitios-rutas/";
//			
//	}
	
	@GetMapping("/sitios-rutas/delete/{sitio}/{ruta}")
	public String deleteSitioRuta(Model model, @PathVariable("sitio") long sitio, @PathVariable("ruta") int ruta, Tmio1SitiosRuta tmio1SitioRuta) {
		
		try {
			Tmio1SitiosRutaWrapper wrapper = new Tmio1SitiosRutaWrapper();
			wrapper.setIdSitio(sitio);
			wrapper.setIdRuta(ruta);
			delegate.deleteSitiosRuta(wrapper);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return "redirect:/sitios-rutas/";
	}
	
	
	
	
	

}
