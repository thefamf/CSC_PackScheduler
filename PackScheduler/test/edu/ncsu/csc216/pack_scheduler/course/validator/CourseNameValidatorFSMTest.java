/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the CourseNameValidatorFSM class.
 * @author mlee25 Michael Lee
 *
 */
public class CourseNameValidatorFSMTest {

	/**
	 * Test method for isValidStateInitial.
	 */
	@Test
	public void testIsValidStateInitial() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		try {
			instance.isValid("1");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}

		try {
			instance.isValid("C216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CS216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}		
	}
	
	/**
	 * Test method for isValidStateL.
	 */
	@Test
	public void testIsValidStateL() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("C?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("C216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CS216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	/**
	 * Test method for isValidStateLL.
	 */
	@Test
	public void testIsValidStateLL() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("CS?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("CS216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSC216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	
	/**
	 * Test method for isValidStateLLL.
	 */
	@Test
	public void testIsValidStateLLL() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("CSC?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("CSC216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	/**
	 * Test method for isValidStateLLLL.
	 */
	@Test
	public void testIsValidStateLLLL() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("CSCX?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("CSCXY");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}

		try {
			instance.isValid("CSCX216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	/**
	 * Test method for isValidStateD.
	 */
	@Test
	public void testIsValidStateD() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("C2?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("C2X");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		try {
			instance.isValid("C216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("C216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	/**
	 * Test method for isValidStateDD.
	 */
	@Test
	public void testIsValidStateDD() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("CSC21?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("CSC21X");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		try {
			instance.isValid("CSC216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	/**
	 * Test method for isValidStateDDD.
	 */
	@Test
	public void testIsValidStateDDD() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("CSC216?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("CSC2160");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}

		try {
			instance.isValid("CSC216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}
	}
	
	/**
	 * Test method for isValidStateSuffix.
	 */
	@Test
	public void testIsValidStateSuffix() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("CSC216A?");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			instance.isValid("CSC216AA");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}

		try {
			instance.isValid("CSC216A1");
			fail(); // InvalidTransitionException should be thrown
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}
	
/*		try {
			instance.isValid("C216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CS216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CS216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSC216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSC216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		} 
		
				try {
			instance.isValid("C216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}


		*/

	

	/**
	 * Test method for isValidStateInitial.
	 */
	@Test
	public void testIsValid() {
		CourseNameValidatorFSM instance = new CourseNameValidatorFSM();

		try {
			instance.isValid("C216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("C216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CS216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CS216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSC216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSC216A");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

		try {
			instance.isValid("CSCX216");
		} catch (InvalidTransitionException e) {
			fail(); // InvalidTransitionException should not be thrown
		}

	}

}
