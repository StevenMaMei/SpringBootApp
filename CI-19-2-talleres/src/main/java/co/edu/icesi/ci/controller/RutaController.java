package co.edu.icesi.ci.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.delegate.Delegate;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

@Controller
public class RutaController {
//	@Autowired
//	private ServicioRuta servicio;
	@Autowired
	private Delegate delegate;
	@GetMapping("/rutas/")
	public String getIndex(Model model) {
//		model.addAttribute("rutas", servicio.findAll());
		model.addAttribute("rutas",delegate.getRutas());
		return "rutas/index";
	}
	@GetMapping("/rutas/add")
	public String addRuta(Model model) {
		model.addAttribute("tmio1Ruta", new Tmio1Ruta());
		return "rutas/add";
	}
	
	@PostMapping("/rutas/add")
	public String saveTmio1Ruta(@Valid Tmio1Ruta tmio1Ruta, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {	
				return "/rutas/add";
			} else {
				try {
//					servicio.guardarRuta(tmio1Ruta);	
					delegate.saveRuta(tmio1Ruta);
					
				}catch(Exception e) {

					if(e.getMessage().equals("La hora de inicio y fin no son consistentes")) {
						bindingResult.rejectValue("horaInicio","error.user", e.getMessage());
						
					}else if(e.getMessage().equals("El dia de inicio y fin no son consistentes")) {
						bindingResult.rejectValue("diaInicio","error.user", e.getMessage());

					}else if (e.getMessage().equals("El fin no es valido")) {
						bindingResult.rejectValue("diaFin", "error.user", e.getMessage());
					}else if(e.getMessage().equals("Ruta con rango de horario no valido")) {
						bindingResult.rejectValue("diaInicio", "error.user", e.getMessage());
					}

					return "/rutas/add";
				}
			}
		}
		return "redirect:/rutas/";
	}
	@GetMapping("/rutas/buscar/")
	public String buscar(Model model,@RequestParam("descripcion")String des ) {
		try {
//			model.addAttribute("rutas",servicio.findByDescripcion(des));
			model.addAttribute("rutas",delegate.findRutaByDescripcion(des));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "rutas/index";
	}

}
