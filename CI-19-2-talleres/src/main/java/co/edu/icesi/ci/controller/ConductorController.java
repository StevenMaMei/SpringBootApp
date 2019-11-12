package co.edu.icesi.ci.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@Controller
public class ConductorController {
	@Autowired
	private ServicioConductor servicio;
	@GetMapping("/conductores/")
	public String getIndex(Model model) {
		model.addAttribute("conductores", servicio.findAll());
		return "/conductores/index";
	}
	@GetMapping("/conductores/add")
	public String addUser(Model model) {
		
		model.addAttribute("tmio1Conductore", new Tmio1Conductore());
		return "/conductores/add";
	}
	
	@PostMapping("/conductores/add")
	public String saveTmio1Conductore(@Valid Tmio1Conductore tmio1Conductore, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {	
				return "/conductores/add";
			} else {
				try {
					servicio.guardarConductor(tmio1Conductore);	
					
				}catch(Exception e) {

					if(e.getMessage().equals("La fecha de contratacion y nacimiento del conductor no son consistentes")) {
						bindingResult.rejectValue("fechaNacimiento","error.user", e.getMessage());
						
					}else {
						bindingResult.rejectValue("cedula","error.user", e.getMessage());

					}

					return "/conductores/add";
				}
			}
		}
		return "redirect:/conductores/";
	}
	
	@GetMapping("/conductores/buscar/")
	public String buscar(Model model,@RequestParam("cedula")String cedula ) {
		try {
			ArrayList<Tmio1Conductore> conducs= new ArrayList<>();
			Tmio1Conductore con= servicio.consultarConductor(cedula);
			if(con!= null) {
				conducs.add(con);
			}else {
				conducs= null;
			}
			model.addAttribute("conductores",conducs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "conductores/index";
	}

}
