package com.ge.ihemsserver.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ge.ihemsserver.models.HomeOccupiedSensor;
import com.ge.ihemsserver.models.TemperatureSensor;
import com.ge.ihemsserver.models.Thermostat;
import com.ge.ihemsserver.models.ThermostatState;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimulatorControllerTests
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Thermostat thermostat;
	@MockBean
	private TemperatureSensor temperatureSensor;
	@MockBean
	private HomeOccupiedSensor homeOccupiedSensor;
	
	@Test
	public void testSetSimulatorState() throws Exception
	{	
		when(thermostat.getState()).thenReturn(StubThermostatState.get());
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/sim/simulator-state")
				.contentType(APPLICATION_JSON_VALUE)
	            .content("{\"actualTemperature\":1,\"homeOccupied\":true}"));
		
		StubThermostatState.check(resultActions);
		
		verify(temperatureSensor).setTemperature(1);
		verify(homeOccupiedSensor).set(true);
	}
	
}
