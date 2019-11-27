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
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

@Controller
public class SitioController {
	
	@Autowired
	private Delegate delegate;
	
	@GetMapping("/sitios/")
	public String indexSitios(Model model) {
		model.addAttribute("sitios",delegate.getSitios());
		return "sitios/index";
	}
	@GetMapping("/sitios/edit/{id}")
	public String editar(Model model, @PathVariable("id") long id) {
		Tmio1Sitio s= new Tmio1Sitio();
		s.setId(id);
		model.addAttribute("tmio1Sitio",s);
		return "sitios/edit";
	}
	
	@GetMapping("/sitios/add")
	public String addBus(Model model) {
		model.addAttribute("tmio1Sitio", new Tmio1Sitio());
		return "sitios/add";
	}
	
	@PostMapping("/sitios/edit")
	public String updateTmio1Sitio(@Valid Tmio1Sitio tmio1Sitio, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {	
				return "sitios/add";
			} else {
				try {
					delegate.updateSitio(tmio1Sitio);
					System.out.println("--------------------------------------------------------------"+tmio1Sitio.getId());
				}catch(Exception e) {

					bindingResult.rejectValue("nombre","error.user", e.getMessage());
					return "sitios/add";
				}
			}
		}
		return "redirect:/sitios/";
	}
	
	@PostMapping("/sitios/add")
	public String saveTmio1Bus(@Valid Tmio1Sitio tmio1Sitio, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {	
				return "sitios/add";
			} else {
				try {
					delegate.saveSitio(tmio1Sitio);
				}catch(Exception e) {

					bindingResult.rejectValue("nombre","error.user", e.getMessage());
					return "sitios/add";
				}
			}
		}
		return "redirect:/sitios/";
	}
	
	@GetMapping("/sitios/buscar/")
	public String buscar(Model model,@RequestParam("id")String id ) {
		try {
			model.addAttribute("sitios",delegate.findSitio(Long.parseLong(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sitios/index";
	}
	
	@GetMapping("/sitios/borrar/{id}")
	public String eliminar(Model model,@PathVariable("id")long id ) {
		try {
			delegate.deleteSitio(id);
			model.addAttribute("sitios",delegate.getSitios());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sitios/index";
	}

}
