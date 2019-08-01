package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Custom InvalidTransitionException class for checked exception in CourseNameValidator.
 * Uses default serialVersionUID of 1L.
 * @author mlee25 Michael Lee
 *
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor for InvalidTransitionException.
	 * @param message String message to pass to parent's constructor
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor for ConflictException.
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}

}
