package com.ge.ihemsserver.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class TestThermostat
{
	private HomeOccupiedSensor mockHomeOccupiedSensor = mock(HomeOccupiedSensor.class);

	private TemperatureSensor mockTemperatureSensor = mock(TemperatureSensor.class);

	private Furnace mockFurnace = mock(Furnace.class);

	private AirCon mockAirCon = mock(AirCon.class);

	private Thermostat thermostat = new Thermostat(mockTemperatureSensor, mockHomeOccupiedSensor, mockFurnace, mockAirCon);

	/**
	 * Story 1.1 - Given the home is occupied and the measured temperature is at or above the desired minimum temperature,
	 * When the measured temperature falls below the desired temperature,
	 * Then the Furnace should start.
	 */
	@Test
	public void testWhenTempFallsBelowDesiredMinThenFurnaceStarts()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(20.0, 19.0);
		thermostat.setDesiredRange(new TempRange(20, 20));
		verify(mockFurnace, never()).start();
		thermostat.update(null, null);
		verify(mockFurnace).start();
	}

	/**
	 * Story 1.2 - Given the home is occupied and the measured temperature is below the desired minimum temperature,
	 * When and the measured temperature rises to greater than or equal to the desired minimum temperature,
	 * Then the Furnace should stop.
	 */
	@Test
	public void testWhenTempRisesAboveDesiredMinThenFurnaceStops()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(19.0, 20.0);
		thermostat.setDesiredRange(new TempRange(20, 20));
		verify(mockFurnace, never()).stop();
		thermostat.update(null, null); 
		verify(mockFurnace).stop();
	}

	/**
	 * Story 1.3 - Given the home is occupied and the measured temperature is at or above the desired minimum temperature,
	 * When the desired minimum temperature is set to a value greater than the measured temperature,
	 * Then the Furnace should start.
	 */
	@Test
	public void testWhenDesiredMinSetHigherThenFurnaceStarts()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(20.0);
		thermostat.setDesiredRange(new TempRange(20, 21));
		verify(mockFurnace).stop();
		thermostat.setDesiredRange(new TempRange(21, 21));
		verify(mockFurnace).start();
	}

	/**
	 * Story 1.4 - Given the home is occupied and the measured temperature is below the desired minimum temperature,
	 * When the desired minimum temperature is set to a value less than or equal to the measured temperature,
	 * Then the Furnace should stop.
	 */
	@Test
	public void testWhenDesiredMinSetLowerThenFurnaceStops()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(20.0);
		thermostat.setDesiredRange(new TempRange(21, 21));
		verify(mockFurnace).start();
		thermostat.setDesiredRange(new TempRange(20, 21));
		verify(mockFurnace).stop();
	}

	/**
	 * Story 1.5 - Given the home is occupied,
	 * When the home is no longer occupied,
	 * Then the Furnace should stop.
	 */
	@Test
	public void testWhenHomeUnoccupiedFurnaceStops()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(10.0, 10.0);
		thermostat.setDesiredRange(new TempRange(20, 20));
		verify(mockFurnace).start();
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(false);
		thermostat.update(null, null);
		verify(mockFurnace).stop();
	}

	/**
	 * Story 2.1 - Given the home is occupied and the measured temperature is below or at the desired maximum temperature,
	 * When the measured temperature rises above the desired maximum temperature,
	 * Then the Air Conditioning should start.
	 */
	@Test
	public void testWhenTempRisesAboveDesiredMaxThenAirConStarts()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(21.0, 22.0);
		thermostat.setDesiredRange(new TempRange(21, 21));
		verify(mockAirCon, never()).start();
		thermostat.update(null, null);
		verify(mockAirCon).start();
	}

	/**
	 * Story 2.2 - Given the home is occupied and the measured temperature is above the desired maximum temperature,
	 * When the measured temperature falls below or equal to the desired maximum temperature,
	 * Then the Air Conditioning should stop.
	 */
	@Test
	public void testWhenTempFallsBelowDesiredMaxThenAirConStops()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(22.0, 21.0);
		thermostat.setDesiredRange(new TempRange(21, 21));
		verify(mockAirCon, never()).stop();
		thermostat.update(null, null);
		verify(mockAirCon).stop();
	}

	/**
	 * Story 2.3 - Given the home is occupied and the measured temperature is below or at the desired maximum temperature, 
	 * When the desired maximum temperature is set to a value less than the measured temperature,
	 * Then the Air Conditioning should start.
	 */
	@Test
	public void testWhenDesiredMaxSetLowerThenAirConStarts()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(21.0);
		thermostat.setDesiredRange(new TempRange(20, 21));
		verify(mockAirCon).stop();
		thermostat.setDesiredRange(new TempRange(20, 20));
		verify(mockAirCon).start();
	}

	/**
	 * Story 2.4 - Given the home is occupied and the measured temperature is above the desired maximum temperature,
	 * When the desired maximum temperature is set to a value greater than or equal to the measured temperature,
	 * Then the Air Conditioning should stop.
	 */
	@Test
	public void testWhenDesiredMaxSetHigherThenAirConStops()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(21.0);
		thermostat.setDesiredRange(new TempRange(20, 20));
		verify(mockAirCon).start();
		thermostat.setDesiredRange(new TempRange(20, 21));
		verify(mockAirCon).stop();
	}

	/**
	 * Story 2.5 - Given the home is occupied,
	 * When the home is no longer occupied, 
	 * Then the Air Conditioning should stop.
	 */
	@Test
	public void testWhenHomeUnoccupiedAirConStops()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(30.0, 30.0);
		thermostat.setDesiredRange(new TempRange(25, 25));
		verify(mockAirCon).start();
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(false);
		thermostat.update(null, null);
		verify(mockAirCon).stop();
	}

	/**
	 * Test that the desired min temperature defaults to 20 and the desired max
	 * temperature defaults to 25
	 */
	@Test
	public void testDefaultDesiredMinMax()
	{
		assertEquals(20, thermostat.getState().getDesiredRange().getMin(), 0);
		assertEquals(25, thermostat.getState().getDesiredRange().getMax(), 0);
	}

	/**
	 * Test that when the sensor temperature changes from above the desired max
	 * temperature to below the desired min temperature then the furnace starts
	 * and the aircon stops
	 */
	@Test
	public void testFurnaceStartsAirconStopsWhenDesiredTemp()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(26.0, 19.0);
		thermostat.setDesiredRange(new TempRange(20, 25));
		verify(mockFurnace).stop();
		verify(mockAirCon).start();
		thermostat.update(null, null);
		verify(mockFurnace).start();
		verify(mockAirCon).stop();
	}

	/**
	 * Test that when the sensor temperature changes from below the desired min
	 * temperature to above the desired max temperature then the aircon starts
	 * and the furnace stops
	 */
	@Test
	public void testAirconStartsFurnaceStopsWhenDesiredTemp()
	{
		when(mockHomeOccupiedSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(19.0, 26.0);
		thermostat.setDesiredRange(new TempRange(20, 25));
		verify(mockFurnace).start();
		verify(mockAirCon).stop();
		thermostat.update(null, null);
		verify(mockFurnace).stop();
		verify(mockAirCon).start();
	}
}
