package co.edu.icesi.ci.controller;

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

@Controller
public class SitiosRutaController {
	
	@Autowired
	private Delegate delegate;
	
	@GetMapping("/sitios-rutas/")
	public String indexSitiosRutas(Model model) {
		model.addAttribute("sitiosrutas", delegate.getSitiosRutas());
		return "sitios-rutas/index";
	}
	
	@GetMapping("sitios-rutas/add")
	public String addSitioRuta(Model model) {
		model.addAttribute("rutas",delegate.getRutas());
		model.addAttribute("sitios",delegate.getSitios());
		model.addAttribute("tmio1SitiosRuta", new Tmio1SitiosRutaPK());
		return "sitios-rutas/add";
	}
	
	@PostMapping("/sitios-rutas/add")
	public String saveSitioRuta(@Valid Tmio1SitiosRutaPK tmio1SitiosRutaPK, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("rutas",delegate.getRutas());
				model.addAttribute("sitios",delegate.getSitios());
				model.addAttribute("tmio1SitiosRuta", new Tmio1SitiosRutaPK());
				return "sitios-rutas/add";
			}
			delegate.saveSitioRuta(tmio1SitiosRutaPK);
		}
		return "redirect:/sitios-rutas/";		
	}
	
	@GetMapping("/sitios-rutas/edit/{hash}")
	public String showUpdateSitioRuta(Model model, @PathVariable("hash") int hash) {
		model.addAttribute("rutas",delegate.getRutas());
		model.addAttribute("sitios",delegate.getSitios());
		model.addAttribute("pk", new Tmio1SitiosRutaPK());
		return "sitios-rutas/edit";
	}
	
	@PostMapping("/sitios-rutas/edit/{hash}")
	public String updateSitioRuta(@PathVariable("hash") int hash, @RequestParam(value = "action", required = true) String action, @Valid Tmio1SitiosRutaPK tmio1SitiosRutaPK, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("rutas",delegate.getRutas());
				model.addAttribute("sitios",delegate.getSitios());
				model.addAttribute("hash", new Tmio1SitiosRutaPK());
				return "sitios-rutas/edit";
			}
			Tmio1SitiosRutaPK id = delegate.findSitiosRuta(hash).getId();
			delegate.deleteSitiosRuta(id);
			Tmio1SitiosRuta t = new Tmio1SitiosRuta();
			t.setId(id);
			t.setTmio1Sitio1(delegate.findSitio(id.getIdSitio()).get(0));
			t.setTmio1Ruta1(delegate.findRutaByDescripcion());
		}
		return "redirect:/sitios-rutas/";
			
	}
	
	@GetMapping("sitios-rutas/find")
	public String find() {
		return null;
	}
	
	public String delete() {
		
	}
	
	
	public String saveSitioRuta() {
		
		
		return null;
	}
	
	
	

}
