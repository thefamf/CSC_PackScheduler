/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ConflictException class.
 * @author mlee25 Michael Lee
 *
 */
public class ConflictExceptionTest {

	/**
	 * Test method ConflictException constructor with String parameter.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for parameterless ConflictException constructor.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
