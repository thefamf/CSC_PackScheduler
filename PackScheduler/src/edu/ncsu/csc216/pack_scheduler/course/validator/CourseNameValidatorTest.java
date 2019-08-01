/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the CourseNameValidator class.
 * @author mlee25 Michael Lee
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Test method for isValidStateInitial.
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void 
	testIsValidInitialState() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
		instance = new CourseNameValidator();
		try {
			instance.isValid("1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		instance.isValid("C");
	}

	/**
	 * Test isValid for LetterStateL
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void testIsValidLetterStateL() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
		instance = new CourseNameValidator();
		instance.isValid("C1");
		instance = new CourseNameValidator();
		instance.isValid("CS");
//		try {
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
		instance.isValid("C1");
		try {
		instance.isValid("CS");
		} catch (InvalidTransitionException e) {
			//valid
		}
//		try {
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
	}
	
	/**
	 * Test method for isValidLetterStateLL.
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void testIsValidLetterStateLL() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
		instance = new CourseNameValidator();
		instance.isValid("CSC");
		instance = new CourseNameValidator();
		instance.isValid("CS1");
	}
	
	
	/**
	 * Test method for isValidStateLLL.
	 */
	@Test
	public void testIsValidLetterStateLLL() {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
		try {
			instance.isValid("1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		try {
			instance.isValid("C");
		} catch (InvalidTransitionException e) {
			//valid
		}
		try {
			instance.isValid("CS");
		} catch (InvalidTransitionException e) {
			//valid
		}
		try {
			instance.isValid("CSC");
		} catch (InvalidTransitionException e) {
			//valid
		}
		try {
			instance.isValid("CSC1");
		} catch (InvalidTransitionException e) {
			//valid
		}
		try {
			instance.isValid("CSCC");
		} catch (InvalidTransitionException e) {
			//valid
		}
	}
	
	/**
	 * Test method for isValidLetterStateLLLL.
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void testIsValidLetterStateLLLL() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
//		try {
//			instance.isValid("1");
//		} catch (InvalidTransitionException e) {
//			assertEquals("Course name must start with a letter.", e.getMessage());
//		}
		instance.isValid("CSCS");
//		try {
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
		instance = new CourseNameValidator();
		instance.isValid("CSCS1");
//		try {
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
//		try {
//			instance.isValid("CSC");
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
//		try {
//			instance.isValid("CSCC");
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
//		try {
//			instance.isValid("CSCC1");
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
//		try {
//			instance.isValid("CSCCS");
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
//		try {
//			instance.isValid("CSCC1");
//		} catch (InvalidTransitionException e) {
//			//valid
//		}
	}
	
	/**
	 * Test method for isValidStateD.
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void testIsValidStateD() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}	
		instance = new CourseNameValidator();
		instance.isValid("C1");
		try {
			instance.isValid("C1A");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
			instance.isValid("C12");
		} catch (InvalidTransitionException e) {
			//valid
		}
	}
	
	/**
	 * Test method for isValidStateDD.
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void testIsValidStateDD() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
		instance = new CourseNameValidator();
		instance.isValid("C121");
		instance = new CourseNameValidator();
		try {
			instance.isValid("C12A");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
			instance.isValid("C12?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	}
	
	/**
	 * Test method for isValidStateDDD.
	 * @throws InvalidTransitionException if invalid transition
	 */
	@Test
	public void testIsValidStateDDD() throws InvalidTransitionException {
		CourseNameValidator instance = new CourseNameValidator();
		try {
			instance.isValid("?");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}		
		instance = new CourseNameValidator();
		instance.isValid("C216A");
		instance = new CourseNameValidator();
		try {
		instance.isValid("C2161");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}		
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC2167");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}		
	}
	
	/**
	 * Test method for isValidStateSuffix.
	 */
	@Test
	public void testIsValidStateSuffix() {
		CourseNameValidator instance = new CourseNameValidator();
		try {
		instance.isValid("CSC216AA");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
			instance.isValid("CSC216A1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}	

	/**
	 * Test method for console output.
	 */
	@Test
	public void testIsValidConsole() {
		CourseNameValidator instance = new CourseNameValidator();
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC1167");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC116@");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSCAB116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC116L7");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC116L$");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC!116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSCA!116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CSC116L"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CS116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CS!C116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC11X6");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC11@6");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("E115"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC1L16");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CSCA116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC116LX");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CS116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("C116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("C!SC116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("CSC1?16");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("!CSC116");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CS116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("C116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		//additional tests
		instance = new CourseNameValidator();
		try {
		assertTrue(instance.isValid("CSCA116A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		instance = new CourseNameValidator();
		try {
		instance.isValid("C11");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

	}
}

