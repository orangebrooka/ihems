package com.ge.ihemsserver.models;

public abstract class Appliance
{
	private boolean isRunning = false;
	
	public void start()
	{	
		isRunning = true;
	}

	public void stop()
	{
		isRunning = false;
	}

	public boolean isRunning()
	{
		return isRunning;
	}

}
