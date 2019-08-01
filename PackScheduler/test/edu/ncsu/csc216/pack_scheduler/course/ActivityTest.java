/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Activity class for checkConflict() method
 * @author mlee25 Michael Lee
 *
 */
public class ActivityTest {

	/**
	 * Test method for checkConflict()
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445);
		
		// test two Activities without a conflict
		try {
			//Call to checkConflict
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
			//If an exception is thrown when there is no conflict the test fails.
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}

		// test two Activities without a conflict, switching the parameters
		try {
			a2.checkConflict(a1);
			assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}

		// test with a1 on same days as a2, with a1 start time at a2 end time
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
		    assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		// repeat test with parameters switched
		try {
			a2.checkConflict(a1);
		} catch (ConflictException e) {
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		// test with a1 on same days as a2, with a1 start time within a2 start and end time
		a1.setMeetingDays("TH");
		a1.setActivityTime(1400, 1530);
		try {
			a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
		    assertEquals("TH 2:00PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		// repeat test with parameters switched
		try {
			a2.checkConflict(a1);
		} catch (ConflictException e) {
			assertEquals("TH 2:00PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		// test with a1 on same days as a2, with a1 end time at a2 start time
		a1.setMeetingDays("TH");
		a1.setActivityTime(1200, 1330);
		try {
			a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
		    assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		// repeat test with parameters switched
		try {
			a2.checkConflict(a1);
		} catch (ConflictException e) {
			assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		// test with a1 on same days as a2, with a1 end time within a2 start and end time
		a1.setMeetingDays("TH");
		a1.setActivityTime(1200, 1430);
		try {
			a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
		    assertEquals("TH 12:00PM-2:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		// repeat test with parameters switched
		try {
			a2.checkConflict(a1);
		} catch (ConflictException e) {
			assertEquals("TH 12:00PM-2:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		// test with a1 sharing 1 day with a2, with a1 start time at a2 end time
		a1.setMeetingDays("TF");
		a1.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
		    assertEquals("TF 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		// repeat test with parameters switched
		try {
			a2.checkConflict(a1);
		} catch (ConflictException e) {
			assertEquals("TF 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		// test two Activities without a conflict, with a1 as Arranged
		a1.setMeetingDays("A");
		a1.setActivityTime(0, 0);
		try {
			a1.checkConflict(a2);
			assertEquals("Arranged", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities were compared and one Activity was Arranged.");
		}
		// repeat test with parameters switched
		try {
			a2.checkConflict(a1);
			assertEquals("Arranged", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities were compared and one Activity was Arranged.");
		}

	}

}
