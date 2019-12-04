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

import co.edu.icesi.ci.delegate.Delegate;
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

//	@Autowired
//	private ServicioServicio servicioServicio;
	
	@Autowired
	private Delegate delegate;

//	@Autowired
//	private ServicioBus servicioBus;
//	
//	@Autowired
//	private ServicioConductor servicioConductor;
//	
//	@Autowired
//	private ServicioRuta servicioRuta;
	
	@GetMapping("/servicios/")
	public String getIndex(Model model) {
//		model.addAttribute("servicios", servicioServicio.findAll());
		model.addAttribute("servicios", delegate.getServicios());
		return "servicios/index";
	}
	
	@GetMapping("/servicios/add/")
	public String addServicio(Model model) {
//		model.addAttribute("conductores", servicioConductor.findAll());
//		model.addAttribute("buses", servicioBus.findAll());
//		model.addAttribute("rutas",servicioRuta.findAll());
//		model.addAttribute("tmio1ServicioWrapper", new Tmio1ServicioWrapper());
		model.addAttribute("conductores", delegate.getConductores());
		model.addAttribute("buses", delegate.getBuses());
		model.addAttribute("rutas",delegate.getRutas());
		model.addAttribute("tmio1ServicioWrapper", new Tmio1ServicioWrapper());
		
		return "servicios/add";
	}
	
	@PostMapping("/servicios/add")
	public String saveTmio1Servicio(@Valid Tmio1ServicioWrapper tmio1ServicioWrapper, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {
//				model.addAttribute("conductores", servicioConductor.findAll());
//				model.addAttribute("buses", servicioBus.findAll());
//				model.addAttribute("rutas",servicioRuta.findAll());
				model.addAttribute("conductores", delegate.getConductores());
				model.addAttribute("buses", delegate.getBuses());
				model.addAttribute("rutas", delegate.getRutas());
				return "/servicios/add";
			} else {
				try {
//					servicioServicio.guardarServicio(Integer.parseInt(tmio1ServicioWrapper.getIdBus()), tmio1ServicioWrapper.getCedulaConductor(), Integer.parseInt(tmio1ServicioWrapper.getRutaId()), tmio1ServicioWrapper.getFechaInicio(), tmio1ServicioWrapper.getFechaFin());	
					delegate.saveServicio(tmio1ServicioWrapper);
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
//					model.addAttribute("conductores", servicioConductor.findAll());
//					model.addAttribute("buses", servicioBus.findAll());
//					model.addAttribute("rutas",servicioRuta.findAll());
					model.addAttribute("conductores", delegate.getConductores());
					model.addAttribute("buses", delegate.getBuses());
					model.addAttribute("rutas", delegate.getRutas());
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
//			Tmio1Servicio s= servicioServicio.consultarServicio(id, ced, idR, new Date(fi), new Date(ff));
			System.out.println("jejeje");
			System.out.println(id.toString());
			w.setIdBus(id.toString());
			w.setIdBusViejo(id.toString());
			w.setCedulaConductor(ced);
			w.setCedulaConductorViejo(ced);
			w.setRutaId(idR.toString());
			w.setRutaIdViejo(idR.toString());
			w.setFechaInicio(new Date(fi));
			w.setFechaInicioViejo(new Date(fi));
			w.setFechaFin(new Date(ff));
			w.setFechaFinViejo(new Date(ff));
			//Tmio1Servicio s= delegate.findServicio(w);
//			if(s!= null) {
//				Tmio1Servicio ser= s;
//				Tmio1ServicioPK pk = ser.getId();
//				w.setCedulaConductor(pk.getCedulaConductor());
//				w.setCedulaConductorViejo(pk.getCedulaConductor());
//				
//				w.setFechaFin(pk.getFechaFin());
//				w.setFechaFinViejo(pk.getFechaFin());
//				
//				w.setFechaInicio(pk.getFechaInicio());
//				w.setFechaInicioViejo(pk.getFechaInicio());
//				
//				w.setIdBus(pk.getIdBus()+"");
//				w.setIdBusViejo(pk.getIdBus()+"");
//				
//				w.setRutaId(pk.getIdRuta()+"");
//				w.setRutaIdViejo(pk.getIdRuta()+"");
				
				model.addAttribute("tmio1ServicioWrapper", w);
				model.addAttribute("conductores", delegate.getConductores());
				model.addAttribute("buses", delegate.getBuses());
				model.addAttribute("rutas",delegate.getRutas());
				return "servicios/edit";
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/servicios/";
	}
	@PostMapping("/servicios/edit/")
	public String updateTmio1Servicio(@Valid @ModelAttribute Tmio1ServicioWrapper tmio1ServicioWrapper, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel")) {			
			if (bindingResult.hasErrors()) {
				model.addAttribute("tmio1ServicioWrapper", tmio1ServicioWrapper);
				model.addAttribute("conductores", delegate.getConductores());
				model.addAttribute("buses", delegate.getBuses());
				model.addAttribute("rutas", delegate.getRutas());
				return "/servicios/edit";
			} else {
				Tmio1Servicio ser= new Tmio1Servicio();
				try {
					System.out.println("lleggoooooo");
					System.out.println(tmio1ServicioWrapper.getIdBus());
					System.out.println(tmio1ServicioWrapper.getIdBusViejo());
					delegate.updateServicio(tmio1ServicioWrapper);
					
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
					model.addAttribute("conductores", delegate.getConductores());
					model.addAttribute("buses", delegate.getBuses());
					model.addAttribute("rutas",delegate.getRutas());
					return "/servicios/edit";
				}
//				try {
//					delegate.updateServicio(tmio1ServicioWrapper);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
		return "redirect:/servicios/";
	}
	
	@GetMapping("/servicios/buscar/")
	public String buscar(Model model, @RequestParam("fecha") Date fecha ) {
		try {
			model.addAttribute("servicios",delegate.findServicioByDate(fecha));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "servicios/index";
	}

}
