package com.przemo.RestAPI.aspect;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class TestAspectForControllers {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	private JoinPoint jointPoint;
	
	@InjectMocks
	private static AspectForControllers aspectForControllers;
	
	
	
	@BeforeClass
	public static void beforeClass() throws SecurityException, IOException
	{
		aspectForControllers = new AspectForControllers();
	}
	
	@Test
	public void checkIfAllLoggsArePrinted()
	{
		boolean isInfoLevel =  Logger.getLogger(AspectForControllers.class.getName()).isLoggable(Level.INFO);
		assertTrue(isInfoLevel);
	}
	
}
