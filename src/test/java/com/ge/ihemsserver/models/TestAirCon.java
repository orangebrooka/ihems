package com.ge.ihemsserver.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAirCon
{
	AirCon airCon = new AirCon();
	
	/**
	 * Test that initially the air con is not running 
	 */
	@Test
	public void testInitiallyNotRunning()
	{
		assertFalse(airCon.isRunning());
	}
	
	/**
	 * Test that after the air con starts isRunning returns true
	 */
	@Test
	public void testStart()
	{
		airCon.start();
		assertTrue(airCon.isRunning());
	}
	
	/**
	 * Test that after the air con stops isRunning returns false
	 */
	@Test
	public void testStop()
	{
		airCon.stop();
		assertFalse(airCon.isRunning());
	}

}
