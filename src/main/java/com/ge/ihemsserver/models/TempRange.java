package com.ge.ihemsserver.models;

public class TempRange
{
	public TempRange(double min, double max)
	{
		this.min = min;
		this.max = max;
	}
	
	public TempRange()
	{
	}

	public double getMin()
	{
		return min;
	}
	public double getMax()
	{
		return max;
	}
	public void setMin(double min)
	{
		this.min = min;
	}
	public void setMax(double max)
	{
		this.max = max;
	}

	private double min;	
	private double max;
}