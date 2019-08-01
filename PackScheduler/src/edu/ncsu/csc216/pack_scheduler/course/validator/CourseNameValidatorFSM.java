package edu.ncsu.csc216.pack_scheduler.course.validator;


/**
 * Finite State Machine for checking whether
 * a Course's Name is valid.
 * 
 * @author Jason King
 * @author Sarah Heckman
 */
public class CourseNameValidatorFSM {

	/** Initial state before input is examined */
	private final int stateInitial = 0;
	
	/** State at which one letter has been identified */
	private final int stateL = 1;
	
	/** State at which two letters have been identified */
	private final int stateLL = 2;
	
	/** State at which three letters have been identified */
	private final int stateLLL = 3;
	
	/** State at which four letters have been identified */
	private final int stateLLLL = 4;
	
	/** State at which one digit has been identified */
	private final int stateD = 5;
	
	/** State at which two digits have been identified */
	private final int stateDD = 6;
	
	/** State at which three digits have been identified */
	private final int stateDDD = 7;
	
	/** State at which a suffix letter has been identified */
	private final int stateSuffix = 8;
	
	/** The state variable keeps track of the current FSM state we are in */
	private int state;
	
	/**
	 * Returns true if the course name is valid, based on
	 * a string matching Finite State Machine.
	 * 
	 * The course name must match the following format:
	 *      (1-3 letters)(3 digits)(optionally, a 1 letter suffix)
	 *      
	 * @param courseName the name of the course
	 * @return true if the course name is valid, or false if the course name is invalid
	 * @throws InvalidTransitionException when the FSM attempts an invalid transition
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		// Set the state field to be the initial FSM state
		state = stateInitial;
		
		// Create a variable to track the current character index
		int charIndex = 0;
		
		// Variable to keep track of the current input character being examined
		char c;
		
		// Iterate through the ID, examining one character at a time
		while(charIndex < courseName.length()) {
			// Set the current character being examined
			c = courseName.charAt(charIndex);
			
			// Throw an exception if the string contains non alphanumeric characters
			if(!Character.isLetter(c) && !Character.isDigit(c)) {
				throw new InvalidTransitionException("Course name can only contain letters and digits.");
			}
			
			// Use a switch statement for the current character
			switch(state) {
				case stateInitial:
					if(Character.isLetter(c)) {
						state = stateL;
					}
					else if(Character.isDigit(c)) {
						throw new InvalidTransitionException("Course name must start with a letter.");
					}
					break;
				
				case stateL:
					if(Character.isLetter(c)) {
						state = stateLL;
					}
					else if (Character.isDigit(c)) {
						state = stateD;
					}
					break;
					
				case stateLL:
					if(Character.isLetter(c)) {
						state = stateLLL;
					}
					else if (Character.isDigit(c)) {
						state = stateD;
					}
					break;
					
				case stateLLL:
					if(Character.isLetter(c)) {
						state = stateLLLL;
					}
					else if (Character.isDigit(c)) {
						state = stateD;
					}
					break;
					
				case stateLLLL:
					if(Character.isLetter(c)) {
						throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
					}
					else if (Character.isDigit(c)) {
						state = stateD;
					}
					break;					
				
				case stateD:
					if(Character.isLetter(c)) {
						throw new InvalidTransitionException("Course name must have 3 digits.");
					}
					else if (Character.isDigit(c)) {
						state = stateDD;
					}
					break;
					
				case stateDD:
					if(Character.isLetter(c)) {
						throw new InvalidTransitionException("Course name must have 3 digits.");
					}
					else if (Character.isDigit(c)) {
						state = stateDDD;
					}
					break;
					
				case stateDDD:
					if(Character.isLetter(c)) {
						state = stateSuffix;
					}
					else if (Character.isDigit(c)) {
						throw new InvalidTransitionException("Course name can only have 3 digits.");
					}
					break;
					
				case stateSuffix:
					if(Character.isLetter(c)) {
						throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
					}
					else if (Character.isDigit(c)) {
						throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
					}
					break;
				default:
					break;
			}
			
			charIndex++;

		}
		
		return state == stateDDD || state == stateSuffix;	
	}

	/**
	 * Method to checkInvalidTransition.
	 * @param possibleInvalidString the String to check
	 * @throws InvalidTransitionException if an invalid transition occurs
	 */
/*	@Override
	public void checkInvalidTransition(String possibleInvalidString) throws InvalidTransitionException {

		if (possibleInvalidString.length() < 4 || possibleInvalidString.length() > 8) {
			throw new IllegalArgumentException();
		}
		
		char[] inputString = possibleInvalidString.toCharArray();
		char inputChar;
		int currentState = this.state;
		int hasFourLetters = 0;
		
		if (currentState == stateInitial) {
			inputChar = inputString[0];
			if (!Character.isLetter(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
		if (currentState == stateL && inputString.length > 1) {
			inputChar = inputString[1];
			if (!Character.isLetter(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
		if (currentState == stateLL && inputString.length > 2) {
			inputChar = inputString[2];
			if (!Character.isLetter(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
		if (currentState == stateLLL && inputString.length > 3) {
			inputChar = inputString[3];
			if (!Character.isLetter(inputChar) || !Character.isDigit(inputChar)) {
				throw new InvalidTransitionException();
			}
			if (Character.isLetter(inputChar)) {
				hasFourLetters = 1;
			}
		}
		
		if (currentState == stateLLLL && inputString.length > 3 + hasFourLetters) {
			inputChar = inputString[3 + hasFourLetters];
			if (!Character.isDigit(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
		if (currentState == stateD && inputString.length > 4 + hasFourLetters) {
			inputChar = inputString[4 + hasFourLetters];
			if (!Character.isDigit(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
		if (currentState == stateDD && inputString.length > 5 + hasFourLetters) {
			inputChar = inputString[5 + hasFourLetters];
			if (!Character.isDigit(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
		if (currentState == stateDDD && inputString.length > 6 + hasFourLetters) {
			inputChar = inputString[6 + hasFourLetters];
			if (!Character.isLetter(inputChar)) {
				throw new InvalidTransitionException();
			}
		}
		
	}
	*/
	
}