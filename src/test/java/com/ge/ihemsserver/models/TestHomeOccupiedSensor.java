package com.ge.ihemsserver.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Observer;

import org.junit.Test;

public class TestHomeOccupiedSensor
{
	HomeOccupiedSensor homeOccupiedSensor = new HomeOccupiedSensor();

	private Observer mockObserver = mock(Observer.class);

	/**
	 * Test the default value is not occupied
	 */
	@Test
	public void testDefault()
	{
		assertFalse(homeOccupiedSensor.isOccupied());
	}
	
	/**
	 * Test that we can get and set the value
	 */
	@Test
	public void testGetAndSet()
	{
		homeOccupiedSensor.set(true);
		assertTrue(homeOccupiedSensor.isOccupied());
		homeOccupiedSensor.set(false);
		assertFalse(homeOccupiedSensor.isOccupied());
	}
	
	/**
	 * Test that toggling the status notifies Observers
	 */
	@Test
	public void testObservableness()
	{
		homeOccupiedSensor.addObserver(mockObserver);
		homeOccupiedSensor.set(true);
		verify(mockObserver).update(homeOccupiedSensor, null);
	}
	
	/**
	 * Test no update on same value
	 */
	@Test
	public void testNoUpdateForSame()
	{
		homeOccupiedSensor.set(false);
		verifyZeroInteractions(mockObserver);
	}

}
