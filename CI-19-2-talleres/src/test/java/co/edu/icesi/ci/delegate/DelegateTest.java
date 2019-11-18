package co.edu.icesi.ci.delegate;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import co.edu.icesi.ci.restController.BusRestController;
import co.edu.icesi.ci.service.ServicioBus;
import co.edu.icesi.ci.service.ServicioConductor;
import co.edu.icesi.ci.service.ServicioRuta;
import co.edu.icesi.ci.service.ServicioServicio;

public class DelegateTest {

	@Mock
	ServicioBus servicioBusMock;
	@Mock
	ServicioConductor servicioConductorMock;
	@Mock
	ServicioRuta servicioRutaMock;
	@Mock
	ServicioServicio servicioServicio;
	
	@Autowired
	@InjectMocks
	BusRestController busController;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

}
