package com.ge.ihemsserver.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Observer;

import org.junit.Test;

public class TestTemperatureSensor
{
	private TemperatureSensor temperatureSensor = new TemperatureSensor();

	private Observer mockObserver = mock(Observer.class);

	/**
	 * Test that the simulated value passed to the temperature sensor is
	 * returned.
	 */
	@Test
	public void testSetGetTemperature()
	{
		double simTemp = 22;
		temperatureSensor.setTemperature(simTemp);
		assertEquals(simTemp, temperatureSensor.getTemperature(), 0.000000001);
	}

	/**
	 * Test that setting the temperature notifies Observers
	 */
	@Test
	public void testObservableness()
	{
		temperatureSensor.addObserver(mockObserver);
		temperatureSensor.setTemperature(20);
		verify(mockObserver).update(temperatureSensor, null);
	}

}
