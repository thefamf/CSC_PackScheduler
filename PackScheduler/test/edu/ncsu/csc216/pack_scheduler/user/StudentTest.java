/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Tests the Student class
 * 
 * @author mlee25 Michael Lee
 *
 */
public class StudentTest {

	/**
	 * Tests the Student constructor with all field parameters.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		
		// Testing for null first name
		Student s = null; 
		try {
			s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Testing for empty first name
		s = null; 
		try {
			s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null last name
		s = null; 
		try {
			s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty last name
		s = null; 
		try {
			s = new Student("first", "", "id", "email@ncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null id
		s = null; 
		try {
			s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty id
		s = null; 
		try {
			s = new Student("first", "last", "", "email@ncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null email
		s = null; 
		try {
			s = new Student("first", "last", "id", null, "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty email
		s = null; 
		try {
			s = new Student("first", "last", "id", "", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for email without '@' character
		s = null; 
		try {
			s = new Student("first", "last", "id", "emailncsu.edu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for email without '.' character
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsuedu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for email with last '.' character before '@' character
		s = null; 
		try {
			s = new Student("first", "last", "id", "email.email@ncsuedu", "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null password
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", null, 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty password
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "", 3);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for max credits below 3
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for max credits above 18
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 19);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for a valid construction
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
			assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
			assertEquals(18, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests the Student constructor with 5 parameters.
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		// Testing for null first name
		Student s = null; 
		try {
			s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 18);
			fail(); 
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Testing for empty first name
		s = null; 
		try {
			s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null last name
		s = null; 
		try {
			s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty last name
		s = null; 
		try {
			s = new Student("first", "", "id", "email@ncsu.edu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null id
		s = null; 
		try {
			s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty id
		s = null; 
		try {
			s = new Student("first", "last", "", "email@ncsu.edu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null email
		s = null; 
		try {
			s = new Student("first", "last", "id", null, "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty email
		s = null; 
		try {
			s = new Student("first", "last", "id", "", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for email without '@' character
		s = null; 
		try {
			s = new Student("first", "last", "id", "emailncsu.edu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for email without '.' character
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsuedu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for email with last '.' character before '@' character
		s = null; 
		try {
			s = new Student("first", "last", "id", "email.email@ncsuedu", "hashedpassword", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for null password
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", null, 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Testing for empty password
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "", 18);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
				
		// Testing for a valid construction
		s = null; 
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
			assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
			assertEquals(18, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}		
	}

	/**
	 * Test setFirstName()
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		// Test that setting first name to null doesn't change the first name (or anything else)
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting first name to "" doesn't change the first name (or anything else)
		try {
		    s.setFirstName("");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Valid set
		s.setFirstName("new first");
	    assertEquals("new first", s.getFirstName());
		assertEquals("last", s.getLastName());
		assertEquals("id", s.getId());
		assertEquals("email@ncsu.edu", s.getEmail());
		assertEquals("hashedpassword", s.getPassword());
	}

	/**
	 * Test setLastName()
	 */
	@Test
	public void testSetLastName() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		// Test that setting last name to null doesn't change the last name (or anything else)
		try {
		    s.setLastName(null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting last name to "" doesn't change the last name (or anything else)
		try {
		    s.setLastName("");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Valid set
		s.setLastName("new last");
	    assertEquals("first", s.getFirstName());
		assertEquals("new last", s.getLastName());
		assertEquals("id", s.getId());
		assertEquals("email@ncsu.edu", s.getEmail());
		assertEquals("hashedpassword", s.getPassword());
	}

	/**
	 * Test setEmail()
	 */
	@Test
	public void testSetEmail() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		// Test that setting email to null doesn't change the email (or anything else)
		try {
		    s.setEmail(null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting email to "" doesn't change the email (or anything else)
		try {
		    s.setEmail("");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting email to "emailncsu.edu" doesn't change the email (or anything else)
		try {
		    s.setEmail("emailncsu.edu");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting email to "email@ncsuedu" doesn't change the email (or anything else)
		try {
		    s.setEmail("email@ncsuedu");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting email to "email.email@ncsuedu" doesn't change the email (or anything else)
		try {
		    s.setEmail("email.email@ncsuedu");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Valid set
		s.setEmail("new.email@ncsu.edu");
	    assertEquals("first", s.getFirstName());
		assertEquals("last", s.getLastName());
		assertEquals("id", s.getId());
		assertEquals("new.email@ncsu.edu", s.getEmail());
		assertEquals("hashedpassword", s.getPassword());
	}

	/**
	 * Test setPassword
	 * 	
	 */
	@Test
	public void testSetPassword() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		// Test that setting password to null doesn't change the password (or anything else)
		try {
		    s.setPassword(null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Test that setting password to "" doesn't change the password (or anything else)
		try {
		    s.setPassword("");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
		}

		// Valid set
		s.setPassword("new password");
	    assertEquals("first", s.getFirstName());
		assertEquals("last", s.getLastName());
		assertEquals("id", s.getId());
		assertEquals("email@ncsu.edu", s.getEmail());
		assertEquals("new password", s.getPassword());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#setMaxCredits(int)}.
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		
		// Test that setting max credits to less than 3 doesn't change max credits (or anything else)
		try {
		    s.setMaxCredits(2);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}

		// Test that setting max credits to greater than 18 doesn't change max credis (or anything else)
		try {
		    s.setMaxCredits(19);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}

		// Valid set
		s.setMaxCredits(3);
	    assertEquals("first", s.getFirstName());
		assertEquals("last", s.getLastName());
		assertEquals("id", s.getId());
		assertEquals("email@ncsu.edu", s.getEmail());
		assertEquals("hashedpassword", s.getPassword());
		assertEquals(3, s.getMaxCredits());
	}

	/**
	 * Test method for compareTo() by creating two Students that are equal and one Student that is greater than the first two.
	 * Tests will check that the first two Students are equal and that the first and second students are less than the third student and vice versa.
	 * Tests will compare Students based on last name, then first name, then unity id.
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("first", "lasta", "id1", "email@ncsu.edu", "hashedpassword", 18);
		Student s2 = new Student("first", "lasta", "id1", "email@ncsu.edu", "hashedpassword", 18);
		Student s3 = new Student("first", "lastb", "id2", "email@ncsu.edu", "hashedpassword", 18);
				
		// test s1 is less than s3 and that s1 is equal to s2 for last name, in both directions
		assertTrue(s1.compareTo(s3) < 0);
		assertTrue(s3.compareTo(s1) > 0);
		assertTrue(s1.compareTo(s2) == 0);
		assertTrue(s2.compareTo(s1) == 0);

		// set last names for all students equal, set first names, and then compare based on first name
		s1.setLastName("last");
		s2.setLastName("last");
		s3.setLastName("last");
		s1.setFirstName("firsta");
		s2.setFirstName("firsta");
		s3.setFirstName("firstb");
		assertTrue(s1.compareTo(s3) < 0);
		assertTrue(s3.compareTo(s1) > 0);
		assertTrue(s1.compareTo(s2) == 0);
		assertTrue(s2.compareTo(s1) == 0);

		// set first names for all students equal, and compare based on id
		s1.setFirstName("first");
		s2.setFirstName("first");
		s3.setFirstName("first");
		assertTrue(s1.compareTo(s3) < 0);
		assertTrue(s3.compareTo(s1) > 0);
		assertTrue(s1.compareTo(s2) == 0);
		assertTrue(s2.compareTo(s1) == 0);
		
	}

	/**
	 * Tests the equalsObject() method by creating two Students that are equal and six additional Students that have different values in each field.
	 * Tests will check that the first two Students are equal and that other six Students are not equal to the first Student.
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s3 = new Student("different", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s4 = new Student("first", "different", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s5 = new Student("first", "last", "different", "email@ncsu.edu", "hashedpassword", 18);
		User s6 = new Student("first", "last", "id", "different@ncsu.edu", "hashedpassword", 18);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "different", 18);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);

		//Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		//Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}

	/**
	 * Test that hashCode works correctly
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s3 = new Student("different", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s4 = new Student("first", "different", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s5 = new Student("first", "last", "different", "email@ncsu.edu", "hashedpassword", 18);
		User s6 = new Student("first", "last", "id", "different@ncsu.edu", "hashedpassword", 18);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "different", 18);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);

		//Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Test that toString returns the correct comma-separated value
	 */
	@Test
	public void testToString() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(string1, s1.toString());
		
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		String string2 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(string2, s2.toString());
	}

	/**
	 * Test method for getSchedule()
	 */
	@Test
	public void testGetSchedule() {
		Student student = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		Schedule schedule = student.getSchedule();
		String[][] array = null;
		array = schedule.getScheduledCourses();
		assertEquals(0, array.length);
		assertEquals("My Schedule", schedule.getTitle());		
	}

	/**
	 * Test method for canAdd()
	 */
	@Test
	public void testCanAdd() {
		Student student = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 4);
		Schedule schedule = student.getSchedule();
		//test null
		Course c0 = null;
		assertFalse(student.canAdd(c0));
		//test course
		Course c1 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		assertTrue(student.canAdd(c1));
		assertTrue(schedule.addCourseToSchedule(c1));
		//test duplicate
		assertFalse(student.canAdd(c1));
		//test conflict
		Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "instructor", 10, "MW", 1200, 1300);
		assertFalse(student.canAdd(c2));
		//test over credit limit
		Course c3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "instructor", 10, "TH", 1200, 1300);
		assertFalse(student.canAdd(c3));

	}


}
