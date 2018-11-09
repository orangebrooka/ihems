package com.ge.ihemsserver.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ge.ihemsserver.models.TempRange;
import com.ge.ihemsserver.models.Thermostat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ThermostatControllerTests
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Thermostat thermostat;
	
	@Before
	public void setup()
	{
		when(thermostat.getState()).thenReturn(StubThermostatState.get());
	}
	
	@Test
	public void testSetDesiredRange() throws Exception
	{
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/desired-range")
				.contentType(APPLICATION_JSON_VALUE).content("{\"min\":15,\"max\":25}"));
		
		StubThermostatState.check(resultActions);
		
		ArgumentCaptor<TempRange> arg = ArgumentCaptor.forClass(TempRange.class);
		verify(thermostat).setDesiredRange(arg.capture());
		assertEquals(15, arg.getValue().getMin(), 0);
		assertEquals(25, arg.getValue().getMax(), 0);
	}

	@Test
	public void testGetThermostatState() throws Exception
	{
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/thermostat-state"));

		StubThermostatState.check(resultActions);
	}

}
