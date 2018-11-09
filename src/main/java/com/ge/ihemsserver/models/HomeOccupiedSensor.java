package com.ge.ihemsserver.models;

import java.util.Observable;

import org.springframework.stereotype.Component;

@Component
public class HomeOccupiedSensor extends Observable
{
	private boolean occupied;
	
	public void set(boolean occupied)
	{
		if (this.occupied != occupied)
		{
			this.occupied = occupied;
			setChanged();
			notifyObservers();
		}
	}

	public boolean isOccupied()
	{
		return occupied;
	}

}
