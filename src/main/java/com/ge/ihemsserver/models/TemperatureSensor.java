package com.ge.ihemsserver.models;

import java.util.Observable;

import org.springframework.stereotype.Component;

@Component
public class TemperatureSensor extends Observable
{		
	double temperature;
	
	public double getTemperature()
	{
		return temperature;
	}

	public void setTemperature(double newTemperature)
	{
		temperature = newTemperature;

        setChanged();
        notifyObservers();
	}

}
