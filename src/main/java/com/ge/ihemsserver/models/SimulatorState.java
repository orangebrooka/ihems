package com.ge.ihemsserver.models;

public class SimulatorState
{
	SimulatorState() {	
	}
	public boolean isHomeOccupied()
	{
		return homeOccupied;
	}
	public double getActualTemperature()
	{
		return actualTemperature;
	}
	boolean homeOccupied;
	double actualTemperature;
}
