package com.ge.ihemsserver.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.ResultActions;

import com.ge.ihemsserver.models.TempRange;
import com.ge.ihemsserver.models.ThermostatState;

public class StubThermostatState
{

	public static ThermostatState get()
	{
		return new ThermostatState(false, true, true, 3.0, new TempRange(4.0, 5.0));
	}

	public static void check(ResultActions resultActions) throws Exception
	{
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("acRunning", is(false)))
				.andExpect(jsonPath("furnaceRunning", is(true)))
				.andExpect(jsonPath("homeOccupied", is(true)))
				.andExpect(jsonPath("actualTemperature", is(3.0)))
				.andExpect(jsonPath("desiredRange.min", is(4.0)))
				.andExpect(jsonPath("desiredRange.max", is(5.0)));
	}
}
