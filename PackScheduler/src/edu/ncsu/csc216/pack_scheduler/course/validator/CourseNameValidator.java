package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * CourseNameValidator class implements the state design pattern to check for a valid course name, which must be 1-4 letters, followed by exactly 3 digits, followed by an optional 1 letter suffix.
 * Class contains four inner classes for the states: InitialState, LetterStateL, LetterStateLL, LetterStateLLL, LetterStateLLLL, NumberStateD, NumberStateDD, NumberStateDDD and SuffixState to represent the valid possible states..
 * @author mlee25 Michael Lee
 */
public class CourseNameValidator {
	
	/** Boolean variable for a valid end state */
	private boolean validEndState = false;
	
	/** State object to represent current state */
	State currentState = new InitialState();
		
	/**
	 * Checks if a course name is valid according to the requirements based on the state pattern.
	 * @param courseName the name to check
	 * @return true if valid
	 * @throws InvalidTransitionException if an invalid transition is triggered
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		currentState = new InitialState();
		// Variable to track of current character index
		int charIndex = 0;
		// Variable to keep track of the current input character being examined
		char c;
		while(charIndex < courseName.length()) {
			// Set the current character being examined
			c = courseName.charAt(charIndex);
			
			if(!Character.isLetter(c) && !Character.isDigit(c)) {
				currentState.onOther();
			}

			if(Character.isDigit(c)) {
				currentState.onDigit();
			}			
			if(Character.isLetter(c)) {
				currentState.onLetter();
			}
		charIndex++;
	}
	return validEndState;
}
	
	/**
	 * Inner (non-static) abstract class for State.  
	 * Contains two abstract methods for valid states and a concrete method for an exception state.
	 * @author mlee25 Michael Lee
	 */
	abstract class State {
		/** 
		 * Abstract method to handle a letter input. 
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public abstract void onLetter() throws InvalidTransitionException; 
		
		/** Abstract method to handle a digit input. 
		 * @throws InvalidTransitionException if the digit input is invalid
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Method to handle any input that is not a letter or a digit.
		 * @throws InvalidTransitionException if called
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * Concrete inner class to represent InitialState in the state design pattern.
	 */
	private class InitialState extends State {
		/** Handles a letter input. */
		public void onLetter() {			
			currentState = new LetterStateL();
		}		
		/**
		 * Handles a digit input.
		 * @throws InvalidTransitionException if the digit input is invalid
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		/** Handles non-letter or non-number input. 
		 * 
		 * @throws InvalidTransitionException if an invalid transition is triggered
		 */
		//@Override
		public void onOther() throws InvalidTransitionException {
			currentState = new InitialState();
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
//			super.onOther();				
//			try {
//			} catch (InvalidTransitionException e) {
//				//invalid
//			}
		}
	}
		
	/**
	 * Concrete inner class to represent LetterStateL in the state design pattern.
	 * @author mlee25 Michael Lee
	 *
	 */
	private class LetterStateL extends State {
		/** Handles a letter input. */
		public void onLetter() {			
			currentState = new LetterStateLL();
		}		
		/** Handles a digit input. */
		public void onDigit() {
			currentState = new NumberStateD();
		}
	}
		
	/**
	 * Concrete inner class to represent LetterStateLL in the state design pattern.
	 * @author mlee25 Michael Lee
	 *
	 */
	private class LetterStateLL extends State {
		/** Handles a letter input. */
		public void onLetter() {			
			currentState = new LetterStateLLL();
		}	
		/** Handles a digit input. */
		public void onDigit() {
			currentState = new NumberStateD();
		}
	}
		
	/**
	 * Concrete inner class to represent LetterStateLLL in the state design pattern.
	 */
	private class LetterStateLLL extends State {		
		/** Handles a letter input. */
		public void onLetter() {			
			currentState = new LetterStateLLLL();
		}	
		/** Handles a digit input. */
		public void onDigit() {
			currentState = new NumberStateD();
		}
	}
		
	/**
	 * Concrete inner class to represent LetterStateLLL in the state design pattern.
	 */
	private class LetterStateLLLL extends State {		
		/**
		 * Handles a letter input.
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public void onLetter() throws InvalidTransitionException {			
			throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
		}
		/**
		 * Handles a digit input.
		 */
		public void onDigit() {
			currentState = new NumberStateD();
		}
	}
		
	/**
	 * Concrete inner class to represent NumberStateD in the state design pattern.
	 */
	private class NumberStateD extends State {		
		/**
		 * Handles a letter input.
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public void onLetter() throws InvalidTransitionException {			
			throw new InvalidTransitionException("Course name must have 3 digits.");
		}	
		/** Handles a digit input. */
		public void onDigit() {
			currentState = new NumberStateDD();
		}
	}
		
	/**
	 * Inner class for NumberStateDD.
	 */
	private class NumberStateDD extends State {
		/**
		 * Handles a letter input.
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public void onLetter() throws InvalidTransitionException {			
			throw new InvalidTransitionException("Course name must have 3 digits.");
		}	
		/** Handles a digit input. */
		public void onDigit() {
			currentState = new NumberStateDDD();
			validEndState = true;
		}
	}
		
	/**
	 * Inner class to represent NumberStateDDD.
	 */
	private class NumberStateDDD extends State {		
		/** Handles a letter input. */
		public void onLetter() {			
			currentState = new SuffixState();
			validEndState = true;
		}	
		/**
		 * Handles a digit input.
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have 3 digits.");
		}
	}
		
	/**
	 * Inner class to represent SuffixState in the state design pattern.
	 */
	private class SuffixState extends State {
		
		/**
		 * Handles a letter input.
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public void onLetter() throws InvalidTransitionException {			
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		/**
		 * Handles a digit input.
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
		
