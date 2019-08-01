/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test class for Schedule class
 * @author mlee25 Michael Lee
 */
public class ScheduleTest {

	/**
	 * Test method for Schedule() constructor.
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		String[][] array = null;
		array = s.getScheduledCourses();
		assertEquals(0, array.length);
		assertEquals("My Schedule", s.getTitle());
	}

	/**
	 * Test method for addCourseToSchedule()
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		String[][] array = null;
		assertEquals("My Schedule", s.getTitle());
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		//add course
		assertTrue(s.addCourseToSchedule(c));
		array = s.getScheduledCourses();
		assertEquals(1, array.length);
		assertEquals("My Schedule", s.getTitle());
		assertEquals("CSC216", array[0][0]);
		assertEquals("001", array[0][1]);
		assertEquals("Programming Concepts - Java", array[0][2]);
		assertEquals("MW 12:00PM-1:00PM", array[0][3]);
		//test adding duplicate course for equals()
		try {
			s.addCourseToSchedule(c);
		} catch (IllegalArgumentException e) {
			//invalid
		}
		//test adding duplicate course for isDuplicate()
		Course c2 = new Course("CSC216", "Programming Concepts - Java", "002", 3, "instructor", 10, "MW", 1200, 1300);
		try {
			s.addCourseToSchedule(c2);
		} catch (IllegalArgumentException e) {
			//invalid
		}		
		//test adding conflict course
		Course c3 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		try {
			s.addCourseToSchedule(c3);
		} catch (IllegalArgumentException e) {
			//invalid
		}		
		
	}

	/**
	 * Test method for removeCourseFromSchedule()
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();
		String[][] array = null;
		assertEquals("My Schedule", s.getTitle());
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		//add course
		s.addCourseToSchedule(c);
		array = s.getScheduledCourses();
		assertEquals(1, array.length);
		assertEquals("My Schedule", s.getTitle());
		assertEquals("CSC216", array[0][0]);
		assertEquals("001", array[0][1]);
		assertEquals("Programming Concepts - Java", array[0][2]);
		assertEquals("MW 12:00PM-1:00PM", array[0][3]);
		//remove non-existent course
		Course c2 = new Course("CSC216", "Intro to Programming - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		assertFalse(s.removeCourseFromSchedule(c2));
		//remove course
		s.removeCourseFromSchedule(c);
		array = s.getScheduledCourses();
		assertEquals(0, array.length);
		
	}

	/**
	 * Test method for resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		Schedule s = new Schedule();
		String[][] array = null;
		array = s.getScheduledCourses();
		assertEquals(0, array.length);
		assertEquals("My Schedule", s.getTitle());
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		//add course
		s.addCourseToSchedule(c);
		array = s.getScheduledCourses();
		assertEquals(1, array.length);
		assertEquals("My Schedule", s.getTitle());
		assertEquals("CSC216", array[0][0]);
		assertEquals("001", array[0][1]);
		assertEquals("Programming Concepts - Java", array[0][2]);
		assertEquals("MW 12:00PM-1:00PM", array[0][3]);
		//reset schedule
		s.resetSchedule();
		array = s.getScheduledCourses();
		assertEquals(0, array.length);
		assertEquals("My Schedule", s.getTitle());
	}

	/**
	 * Test method for getScheduledCourses()
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule s = new Schedule();
		String[][] array = null;
		array = s.getScheduledCourses();
		assertEquals(0, array.length);
		assertEquals("My Schedule", s.getTitle());
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		//add course
		s.addCourseToSchedule(c);
		//get schedule
		array = s.getScheduledCourses();
		assertEquals(1, array.length);
		assertEquals("My Schedule", s.getTitle());
		assertEquals("CSC216", array[0][0]);
		assertEquals("001", array[0][1]);
		assertEquals("Programming Concepts - Java", array[0][2]);
		assertEquals("MW 12:00PM-1:00PM", array[0][3]);
		assertEquals("10", array[0][4]);
	}

	/**
	 * Test method for setTitle()
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		String[][] array = null;
		array = s.getScheduledCourses();
		assertEquals(0, array.length);
		assertEquals("My Schedule", s.getTitle());
		//test null title
		try {
			s.setTitle("New Schedule");
		} catch (IllegalArgumentException e) {
			//invalid
		}
		s.setTitle("New Schedule");
		assertEquals("New Schedule", s.getTitle());
	}

	/**
	 * Test method for getScheduleCredits()
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		//add course
		Course c1 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(3, s.getScheduleCredits());
		Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "instructor", 10, "TH", 1200, 1300);
		assertTrue(s.addCourseToSchedule(c2));
		assertEquals(7, s.getScheduleCredits());
	}

	/**
	 * Test method for canAdd()
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		//test course
		Course c1 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "instructor", 10, "MW", 1200, 1300);
		assertTrue(s.canAdd(c1));
		assertTrue(s.addCourseToSchedule(c1));

		//Test null
		Course c2 = null;
		assertFalse(s.canAdd(c2));

		//Test already in schedule
		assertFalse(s.canAdd(c1));
		//Test conflict
		Course c3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "instructor", 10, "MW", 1200, 1300);
		assertFalse(s.canAdd(c3));
	}

}
