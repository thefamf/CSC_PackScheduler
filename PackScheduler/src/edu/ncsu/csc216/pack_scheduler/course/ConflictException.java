/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Custom ConflictException class for checked Exceptions in Activity.
 * Uses default serialVersionUID of 1L.
 * @author mlee25 Michael Lee
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor for ConflictException.
	 * @param message String message to pass to parent's constructor
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor for ConflictException.
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
}
