package co.edu.icesi.ci.talleres.model;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Tmio1ServicioWrapper {
	
	private String idBusViejo;
	private String cedulaConductorViejo;
	private String rutaIdViejo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date fechaInicioViejo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date fechaFinViejo;
	@NotBlank
	private String idBus;
	@NotBlank
	private String cedulaConductor;
	@NotBlank
	private String rutaId;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private java.util.Date fechaInicio;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private java.util.Date fechaFin;

}
