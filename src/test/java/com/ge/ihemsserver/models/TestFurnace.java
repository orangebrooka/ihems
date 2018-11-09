package com.ge.ihemsserver.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFurnace
{
	Furnace furnace = new Furnace();
	
	/**
	 * Test that initially the furnace is not running 
	 */
	@Test
	public void testInitiallyNotRunning()
	{
		assertFalse(furnace.isRunning());
	}
	
	/**
	 * Test that after the furnace starts isRunning returns true
	 */
	@Test
	public void testStart()
	{
		furnace.start();
		assertTrue(furnace.isRunning());
	}
	
	/**
	 * Test that after the furnace stops isRunning returns false
	 */
	@Test
	public void testStop()
	{
		furnace.stop();
		assertFalse(furnace.isRunning());
	}

}
