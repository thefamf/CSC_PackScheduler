package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the InvalidTransitionException class.
 * @author mlee25 Michael Lee
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Test method for InvalidTransitionException constructor with String parameter.
	 */
	@Test
	public void testConflictExceptionString() {
		InvalidTransitionException e = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", e.getMessage());
	}

	/**
	 * Test method for parameterless InvalidTransitionException constructor.
	 */
	@Test
	public void testConflictException() {
		InvalidTransitionException e = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", e.getMessage());
	}
	
}
