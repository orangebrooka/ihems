package com.ge.ihemsserver.models;


import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Thermostat implements Observer
{
	private Furnace furnace;

	private AirCon airCon;

	private TemperatureSensor temperatureSensor;

	private HomeOccupiedSensor homeOccupiedSensor;

	private TempRange desiredRange = new TempRange(20, 25);

	@Autowired
	public Thermostat(TemperatureSensor temperatureSensor, HomeOccupiedSensor homeOccupiedSensor, Furnace furnace, AirCon airCon)
	{
		this.furnace = furnace;
		this.airCon = airCon;
		this.temperatureSensor = temperatureSensor;
		this.homeOccupiedSensor = homeOccupiedSensor;
		
		temperatureSensor.addObserver(this);
		homeOccupiedSensor.addObserver(this);
	}

	public void update(Observable arg0, Object arg1)
	{
		if (homeOccupiedSensor.isOccupied())
		{
			double actualTemperature = temperatureSensor.getTemperature();

			if (actualTemperature < desiredRange.getMin())
				furnace.start();
			else
				furnace.stop();

			if (actualTemperature > desiredRange.getMax())
				airCon.start();
			else
				airCon.stop();
		}
		else
		{
			furnace.stop();
			airCon.stop();
		}
	}

	public ThermostatState getState()
	{
		return new ThermostatState(airCon.isRunning(), furnace.isRunning(), homeOccupiedSensor.isOccupied(), temperatureSensor.getTemperature(), desiredRange);
	}

	public void setDesiredRange(TempRange range)
	{
		desiredRange = range;
		update(null, null);
	}

}