package com.ge.ihemsserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.ihemsserver.models.TempRange;
import com.ge.ihemsserver.models.Thermostat;
import com.ge.ihemsserver.models.ThermostatState;

@RestController
@RequestMapping("api/")
public class ThermostatController
{
	@Autowired
	private Thermostat thermostat;
	
	@GetMapping("/thermostat-state")
	public ThermostatState getThermostatState()
	{
		return thermostat.getState();
	}

	@PutMapping("/desired-range")
	@ResponseBody
	public ThermostatState setDesiredRange(@RequestBody TempRange desiredRange)
	{	
		thermostat.setDesiredRange(desiredRange);
		return thermostat.getState();
	}
}
