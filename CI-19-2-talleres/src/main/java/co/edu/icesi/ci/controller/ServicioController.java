package co.edu.icesi.ci.controller;

import java.sql.Date;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.service.ServicioServicio;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioWrapper;

@Controller
public class ServicioController {

	@Autowired
	private ServicioServicio servicioServicio;
	

	@Autowired
	private ServicioBus servicioBus;
	
	@Autowired
	private ServicioConductor servicioConductor;
	
	@Autowired
	private ServicioRuta servicioRuta;
	
	@GetMapping("/servicios/")
	public String getIndex(Model model) {
		model.addAttribute("servicios", servicioServicio.findAll());
		return "servicios/index";
	}
	
	@GetMapping("/servicios/add/")
	public String addServicio(Model model) {
		model.addAttribute("conductores", servicioConductor.findAll());
		model.addAttribute("buses", servicioBus.findAll());
		model.addAttribute("rutas",servicioRuta.findAll());
		model.addAttribute("tmio1ServicioWrapper", new Tmio1ServicioWrapper());
		
		return "servicios/add";
	}
	
	@PostMapping("/servicios/add")
	public String saveTmio1Servicio(@Valid Tmio1ServicioWrapper tmio1ServicioWrapper, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {
				model.addAttribute("conductores", servicioConductor.findAll());
				model.addAttribute("buses", servicioBus.findAll());
				model.addAttribute("rutas",servicioRuta.findAll());
				return "/servicios/add";
			} else {
				try {
					servicioServicio.guardarServicio(Integer.parseInt(tmio1ServicioWrapper.getIdBus()), tmio1ServicioWrapper.getCedulaConductor(), Integer.parseInt(tmio1ServicioWrapper.getRutaId()), tmio1ServicioWrapper.getFechaInicio(), tmio1ServicioWrapper.getFechaFin());	
					
				}catch(Exception e) {

					if(e.getMessage().equals("No existe el bus")) {
						bindingResult.rejectValue("buses","error.user", e.getMessage());
						
					}else if(e.getMessage().equals("No existe el conductor")) {
						bindingResult.rejectValue("conductor","error.user", e.getMessage());

					}else if (e.getMessage().equals("No existe la ruta")) {
						bindingResult.rejectValue("rut", "error.user", e.getMessage());
					}else if(e.getMessage().equals("La fecha de inicio y fin del servicio no son consistentes")) {
						bindingResult.rejectValue("fechaInicio", "error.user", e.getMessage());
					}else if(e.getMessage().equals("La fecha de inicio del servicio es anterior a la fecha de contratacion")) {
						bindingResult.rejectValue("fechaInicio", "error.user", e.getMessage());
					}
					model.addAttribute("conductores", servicioConductor.findAll());
					model.addAttribute("buses", servicioBus.findAll());
					model.addAttribute("rutas",servicioRuta.findAll());
					return "/servicios/add";
				}
			}
		}
		return "redirect:/servicios/";
	}
	
	@GetMapping("/servicios/edit/{id}/{ced}/{idR}/{fi}/{ff}")
	public String editService(Model model, @PathVariable("id") Integer id, @PathVariable("ced") String ced, @PathVariable("idR") Integer idR, @PathVariable("fi") long fi, @PathVariable("ff") long ff) {
		Tmio1ServicioWrapper w = new Tmio1ServicioWrapper();
		try {
			Tmio1Servicio s= servicioServicio.consultarServicio(id, ced, idR, new Date(fi), new Date(ff));
			if(s!= null) {
				Tmio1Servicio ser= s;
				Tmio1ServicioPK pk = ser.getId();
				w.setCedulaConductor(pk.getCedulaConductor());
				w.setCedulaConductorViejo(pk.getCedulaConductor());
				
				w.setFechaFin(pk.getFechaFin());
				w.setFechaFinViejo(pk.getFechaFin());
				
				w.setFechaInicio(pk.getFechaInicio());
				w.setFechaInicioViejo(pk.getFechaInicio());
				
				w.setIdBus(pk.getIdBus()+"");
				w.setIdBusViejo(pk.getIdBus()+"");
				
				w.setRutaId(pk.getIdRuta()+"");
				w.setRutaIdViejo(pk.getIdRuta()+"");
				
				model.addAttribute("tmio1ServicioWrapper", w);
				model.addAttribute("conductores", servicioConductor.findAll());
				model.addAttribute("buses", servicioBus.findAll());
				model.addAttribute("rutas",servicioRuta.findAll());
				return "servicios/edit";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/servicios/";
	}
	@PostMapping("/servicios/edit/")
	public String updateTmio1Servicio(@Valid Tmio1ServicioWrapper tmio1ServicioWrapper, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {
				model.addAttribute("tmio1ServicioWrapper", tmio1ServicioWrapper);
				model.addAttribute("conductores", servicioConductor.findAll());
				model.addAttribute("buses", servicioBus.findAll());
				model.addAttribute("rutas",servicioRuta.findAll());
				return "/servicios/edit";
			} else {
				Tmio1Servicio ser= new Tmio1Servicio();
				try {
					Tmio1Servicio s= servicioServicio.consultarServicio(Integer.parseInt(tmio1ServicioWrapper.getIdBusViejo()), tmio1ServicioWrapper.getCedulaConductorViejo(), Integer.parseInt(tmio1ServicioWrapper.getRutaIdViejo()), tmio1ServicioWrapper.getFechaInicioViejo(), tmio1ServicioWrapper.getFechaFinViejo());
					if(s!= null) {
						
						
						ser.setTmio1Bus(servicioBus.consultarBus(Integer.parseInt(tmio1ServicioWrapper.getIdBus())));
						ser.setTmio1Conductore(servicioConductor.consultarConductor(tmio1ServicioWrapper.getCedulaConductor()));
						ser.setTmio1Ruta(servicioRuta.consultarRuta(Integer.parseInt(tmio1ServicioWrapper.getRutaId())));
						
						Tmio1ServicioPK pk = new Tmio1ServicioPK();
						pk.setCedulaConductor(tmio1ServicioWrapper.getCedulaConductor());
						pk.setFechaFin(tmio1ServicioWrapper.getFechaFin());
						pk.setFechaInicio(tmio1ServicioWrapper.getFechaInicio());
						pk.setIdBus(Integer.parseInt(tmio1ServicioWrapper.getIdBus()));
						pk.setIdRuta(Integer.parseInt(tmio1ServicioWrapper.getRutaId()));
						
						ser.setId(pk);
						
						servicioServicio.actualizarServicio(ser);
						servicioServicio.removerServicio(s);
					}
					
				}catch(Exception e) {
					e.printStackTrace();
					if(e.getMessage().equals("No existe el bus")) {
						bindingResult.rejectValue("buses","error.user", e.getMessage());
						
					}else if(e.getMessage().equals("No existe el conductor")) {
						bindingResult.rejectValue("conductor","error.user", e.getMessage());

					}else if (e.getMessage().equals("No existe la ruta")) {
						bindingResult.rejectValue("rut", "error.user", e.getMessage());
					}else if(e.getMessage().equals("La fecha de inicio y fin del servicio no son consistentes")) {
						bindingResult.rejectValue("fechaInicio", "error.user", e.getMessage());
					}else if(e.getMessage().equals("La fecha de inicio del servicio es anterior a la fecha de contratacion")) {
						bindingResult.rejectValue("fechaInicio", "error.user", e.getMessage());
					}else {
						bindingResult.rejectValue("fechaInicio", "error.user", e.getMessage());
					}
					model.addAttribute("tmio1ServicioWrapper", tmio1ServicioWrapper);
					model.addAttribute("conductores", servicioConductor.findAll());
					model.addAttribute("buses", servicioBus.findAll());
					model.addAttribute("rutas",servicioRuta.findAll());
					return "/servicios/edit";
				}
				try {
					servicioServicio.actualizarServicio(ser);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/servicios/";
	}
	
	@GetMapping("/servicios/buscar/")
	public String buscar(Model model, @RequestParam("fecha") Date fecha ) {
		try {
			model.addAttribute("servicios",servicioServicio.findByDate(fecha));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "servicios/index";
	}

}
