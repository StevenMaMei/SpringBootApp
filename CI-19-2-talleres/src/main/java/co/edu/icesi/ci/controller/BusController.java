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
import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;


@Controller
public class BusController {
//	@Autowired
//	private ServicioBus servicio;
	
	@Autowired
	private Delegate delegate;
	
	@GetMapping("/buses/")
	public String indexBuses(Model model) {
//		model.addAttribute("buses",servicio.findAll());
		model.addAttribute("buses",delegate.getBuses());
		return "buses/index";
	}
	@GetMapping("/buses/add")
	public String addBus(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		return "buses/add";
	}
	
	@PostMapping("/buses/add")
	public String saveTmio1Bus(@Valid Tmio1Bus tmio1Bus, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {	
				return "buses/add";
			} else {
				try {
//					servicio.guardarBus(tmio1Bus);
					delegate.saveBus(tmio1Bus);
				}catch(Exception e) {

					if(e.getMessage().equals("ya existe un bus con esa placa")) {
						bindingResult.rejectValue("placa","error.user", e.getMessage());
						
					}else {
						bindingResult.rejectValue("tipo","error.user", e.getMessage());

					}

					return "buses/add";
				}
			}
		}
		return "redirect:/buses/";
	}
	
	@GetMapping("/buses/buscar/")
	public String buscar(Model model,@RequestParam("placa")String placa ) {
		try {
//			model.addAttribute("buses",servicio.consultarBus(placa));
			model.addAttribute("buses",delegate.buscarBus(placa));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "buses/index";
	}

}
