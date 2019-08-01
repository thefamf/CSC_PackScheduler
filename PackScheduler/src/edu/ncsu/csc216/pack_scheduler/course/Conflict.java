/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface to define Conflict behavior.
 * @author mlee25 Michael Lee
 *
 */
public interface Conflict {

	/**
	 * Abstract method to check if an Activity causes a Conflict.
	 * @param possibleConflictingActivity the Activity to check for a Conflict.
	 * @throws ConflictException if the Activity causes a Conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

	
}
