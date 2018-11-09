package com.ge.ihemsserver.models;

public class ThermostatState
{
	public ThermostatState(boolean acRunning, boolean furnaceRunning, boolean homeOccupied, double actualTemperature,
			TempRange desiredRange)
	{
		this.acRunning = acRunning;
		this.furnaceRunning = furnaceRunning;
		this.homeOccupied = homeOccupied;
		this.actualTemperature = actualTemperature;
		this.desiredRange = desiredRange;
	}

	public boolean isAcRunning()
	{
		return acRunning;
	}

	public boolean isFurnaceRunning()
	{
		return furnaceRunning;
	}

	public boolean isHomeOccupied()
	{
		return homeOccupied;
	}

	public double getActualTemperature()
	{
		return actualTemperature;
	}

	public TempRange getDesiredRange()
	{
		return desiredRange;
	}

	private boolean acRunning;
	private boolean furnaceRunning;
	private boolean homeOccupied;
	private double actualTemperature;
	private TempRange desiredRange;
}