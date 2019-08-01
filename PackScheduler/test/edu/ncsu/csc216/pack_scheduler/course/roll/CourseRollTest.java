/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Test class for CourseRoll class
 * @author mlee25 Michael Lee
 *
 */
public class CourseRollTest {

	/** Valid student records */
	private final String validStudentFile = "test-files/student_records.txt";

	/**
	 * Test CourseRoll constructor
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();		
		assertEquals(10, roll.getEnrollmentCap());
		assertEquals(10, roll.getOpenSeats());		

		//test enrollmentCap below MIN
		try {
			roll = new CourseRoll(c, 9);
		} catch (IllegalArgumentException e) {
			assertEquals("invalid enrollmentCap", e.getMessage());
		}
		
		//test enrollmentCap above MAX
		try {
			roll = new CourseRoll(c, 251);
		} catch (IllegalArgumentException e) {
			assertEquals("invalid enrollmentCap", e.getMessage());
		}
	}

	/**
	 * Test setEnrollmentCap()
	 */
	@Test
	public void testSetEnrollmentCap() {
		//test valid enrollmentCap
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 50);
		assertEquals(50, cr.getEnrollmentCap());
		assertEquals(50, cr.getOpenSeats());

		//Revise enrollmentCap lower
		cr.setEnrollmentCap(10);
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());

		cr = new CourseRoll(c, 20);
		StudentDirectory sd = new StudentDirectory();
		Student s = null;

		//Load Student Directory
		sd.loadStudentsFromFile(validStudentFile);

		//Enroll the 10 students in student_records.txt
		for (int i = 0; i < 10; i++) {
			String id = sd.getStudentDirectory()[i][2];
			s = sd.getStudentById(id);
			assertTrue(cr.canEnroll(s));
			try {
				cr.enroll(s);
			} catch (IllegalArgumentException e) {
				//invalid
			}
		}
		assertEquals(10, cr.getOpenSeats());

		//Add 11th student and try setting enrollmentCap to 10
		s = new Student("first", "last", "id", "id@ncsu.edu", "pw", 3);
		assertTrue(cr.canEnroll(s));
		try {
			cr.enroll(s);
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(9, cr.getOpenSeats());
		try {
			cr.setEnrollmentCap(10);
			fail();
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(9, cr.getOpenSeats());
		
	
	}

	/**
	 * Test enroll()
	 */
	@Test
	public void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();		
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());		
		assertEquals(0, cr.getNumberOnWaitlist());		

		//Load Student Directory
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validStudentFile);
		Student s = null;

		//Enroll the 10 students in student_records.txt
		for (int i = 0; i < 10; i++) {
			String id = sd.getStudentDirectory()[i][2];
			s = sd.getStudentById(id);
			assertTrue(cr.canEnroll(s));
			try {
				cr.enroll(s);
			} catch (IllegalArgumentException e) {
				assertEquals("waitlist full", e.getMessage());
			}
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		
		//Confirm students
		String id = null;
		for (int i = 0; i < 10; i++) {
			id = sd.getStudentDirectory()[i][2];			
			s = sd.getStudentById(id);
			assertEquals(id, s.getId());
			assertFalse(cr.canEnroll(s));
		}
		assertEquals(0, cr.getNumberOnWaitlist());
		
		//Test adding student to waitlist
		Student s1 = new Student("new", "student", "nstudent1", "nstudent@ncsu.edu", "pw", 18);
		assertTrue(cr.canEnroll(s1));
		try {
			cr.enroll(s1);				
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		
		//Test enroll student already on main roll
		id = sd.getStudentDirectory()[0][2];
		s = sd.getStudentById(id);
		assertFalse(cr.canEnroll(s));

		assertFalse(cr.canEnroll(s1));
		try {
			cr.enroll(s1);				
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());

		//Test fill waitlist
		for (int i = 2; i <= 10; i++) {
			Student s2 = new Student("new", "student", "nstudent" + i, "nstudent@ncsu.edu", "pw", 18);
			assertTrue(cr.canEnroll(s2));
			try {
				cr.enroll(s2);				
			} catch (IllegalArgumentException e) {
				//invalid
			}			
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(10, cr.getNumberOnWaitlist());
		Student s11 = new Student("new", "student", "nstudent11", "nstudent@ncsu.edu", "pw", 18);
		assertFalse(cr.canEnroll(s11));
		try {
			cr.enroll(s11);
		} catch (IllegalArgumentException e) {
			assertEquals("waitlist full", e.getMessage());
		}
	}

	/**
	 * Test drop()
	 */
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();		

		//Load Student Directory
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validStudentFile);
		Student s = null;

		//Enroll the 10 students in student_records.txt
		for (int i = 0; i < 10; i++) {
			String id = sd.getStudentDirectory()[i][2];
			s = sd.getStudentById(id);
			cr.enroll(s);
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		
		//Confirm students
		String id = null;
		for (int i = 0; i < 10; i++) {
			id = sd.getStudentDirectory()[i][2];			
			s = sd.getStudentById(id);
			assertEquals(id, s.getId());
			assertFalse(cr.canEnroll(s));
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		
		//Test adding student to waitlist
		Student s1 = new Student("new", "student", "nstudent1", "nstudent@ncsu.edu", "pw", 18);
		assertTrue(cr.canEnroll(s1));
		cr.enroll(s1);				
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());

		//Drop student from waiting list
		cr.drop(s1);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());

		//Add 3 students to waiting list
		cr.enroll(s1);				
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		Student s2 = new Student("new", "student", "nstudent2", "nstudent@ncsu.edu", "pw", 18);
		Student s3 = new Student("new", "student", "nstudent3", "nstudent@ncsu.edu", "pw", 18);
		cr.enroll(s2);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(2, cr.getNumberOnWaitlist());
		cr.enroll(s3);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(3, cr.getNumberOnWaitlist());

		//Drop Student s2 from waiting list
		cr.drop(s2);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(2, cr.getNumberOnWaitlist());
		
		//Drop student from front
		id =  sd.getStudentDirectory()[0][2];
		s = sd.getStudentById(id);
		assertEquals("daustin", s.getId());
		cr.drop(s);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());

		//Drop student from back
		id = sd.getStudentDirectory()[9][2];
		s = sd.getStudentById(id);
		cr.drop(s);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		assertTrue(cr.canEnroll(s));

		//Drop student from middle
		id = sd.getStudentDirectory()[3][2];
		s = sd.getStudentById(id);
		cr.drop(s);
		assertEquals(1, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		assertTrue(cr.canEnroll(s));

	}

	/**
	 * Test getOpenSeats()
	 */
	@Test
	public void testGetOpenSeats() {
		//Load Student Directory
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 10);
		StudentDirectory sd = new StudentDirectory();
		Student s = null;
		sd.loadStudentsFromFile(validStudentFile);
		assertEquals(10, cr.getOpenSeats());
		//Enroll the 10 students in student_records.txt
		for (int i = 0; i < 10; i++) {
			String id = sd.getStudentDirectory()[i][2];
			s = sd.getStudentById(id);
			assertTrue(cr.canEnroll(s));
			try {
				cr.enroll(s);				
			} catch (IllegalArgumentException e) {
				//invalid
			}
		}
		assertEquals(0, cr.getOpenSeats());

		//Add capacity
		cr.setEnrollmentCap(20);
		assertEquals(10, cr.getOpenSeats());

		//Enroll student and check open seats
		Student s1 = new Student("new", "student", "nstudent", "nstudent@ncsu.edu", "pw", 18);
		try {
			cr.enroll(s1);				
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(9, cr.getOpenSeats());
				
	}

	/**
	 * Test getNumberOnWaitlist()
	 */
	@Test
	public void testGetNumberOnWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();		
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());		
		assertEquals(0, cr.getNumberOnWaitlist());		

		//Load Student Directory
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validStudentFile);
		Student s = null;

		//Enroll the 10 students in student_records.txt
		for (int i = 0; i < 10; i++) {
			String id = sd.getStudentDirectory()[i][2];
			s = sd.getStudentById(id);
			assertTrue(cr.canEnroll(s));
			try {
				cr.enroll(s);
			} catch (IllegalArgumentException e) {
				assertEquals("waitlist full", e.getMessage());
			}
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());

		//Confirm students
		String id = null;
		for (int i = 0; i < 10; i++) {
			id = sd.getStudentDirectory()[i][2];			
			s = sd.getStudentById(id);
			assertEquals(id, s.getId());
			assertFalse(cr.canEnroll(s));
		}
		assertEquals(0, cr.getNumberOnWaitlist());
		
		//Test adding student to waitlist
		Student s1 = new Student("new", "student", "nstudent1", "nstudent@ncsu.edu", "pw", 18);
		assertTrue(cr.canEnroll(s1));
		try {
			cr.enroll(s1);				
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		
		//Test fill waitlist
		for (int i = 2; i <= 10; i++) {
			Student s2 = new Student("new", "student", "nstudent" + i, "nstudent@ncsu.edu", "pw", 18);
			assertTrue(cr.canEnroll(s2));
			try {
				cr.enroll(s2);				
			} catch (IllegalArgumentException e) {
				//invalid
			}			
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(10, cr.getNumberOnWaitlist());
		Student s11 = new Student("new", "student", "nstudent11", "nstudent@ncsu.edu", "pw", 18);
		assertFalse(cr.canEnroll(s11));
		try {
			cr.enroll(s11);
		} catch (IllegalArgumentException e) {
			assertEquals("waitlist full", e.getMessage());
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(10, cr.getNumberOnWaitlist());

		//Drop student from waiting list
		s1 = new Student("new", "student", "nstudent1", "nstudent@ncsu.edu", "pw", 18);
		cr.drop(s1);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(9, cr.getNumberOnWaitlist());



	}
		
		
		/**
	 * Test canEroll()
	 */
	@Test
	public void testCanEnroll() {
		//Load Student Directory
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validStudentFile);
		assertEquals(10, sd.getStudentDirectory().length);

		Student s = null;
		//Enroll the 10 students in student_records.txt
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = null;
		cr = new CourseRoll(c, 10);
		assertEquals(10, cr.getOpenSeats());
		for (int i = 0; i < 10; i++) {
			String id = sd.getStudentDirectory()[i][2];
			s = sd.getStudentById(id);
			assertTrue(cr.canEnroll(s));
			try {
				cr.enroll(s);
			} catch (IllegalArgumentException e) {
				//invalid
			}
		}
		assertEquals(0, cr.getOpenSeats());

		//Confirm students
		String id = null;
		for (int i = 0; i < 10; i++) {
			id = sd.getStudentDirectory()[i][2];			
			s = sd.getStudentById(id);
			assertEquals(id, s.getId());
			assertFalse(cr.canEnroll(s));
		}
		assertEquals(0, cr.getNumberOnWaitlist());

		//Test student over capacity
		s = sd.getStudentById("zking");
		assertFalse(cr.canEnroll(s));
		
		//Add capacity
		cr.setEnrollmentCap(11);
		assertEquals(1, cr.getOpenSeats());

		//Test add new student 
		s = new Student("first", "last", "id", "id@email.edu", "pw");
		assertEquals(1, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s));
		try {
			cr.enroll(s);
		} catch (IllegalArgumentException e) {
			assertEquals("", e.getMessage());
		}
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
		
		//Test canEnroll duplicate new student 
		assertFalse(cr.canEnroll(s));

		//Test add new student2 
		Student s2 = new Student("first2", "last2", "id2", "id2@email.edu", "pw");
		assertTrue(cr.canEnroll(s2));
		cr.enroll(s2);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());

		//Test canEnroll duplicate new student2 
		assertFalse(cr.canEnroll(s2));
		try {
			cr.enroll(s2);			
		} catch (IllegalArgumentException e) {
			assertEquals("duplicate student", e.getMessage());
		}
		
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		
		//Test duplicate student 
		s = sd.getStudentById("zking");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("cschwartz");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("shansen");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("daustin");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("rbrennan");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("efrost");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("lberg");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("gstone");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("ahicks");
		assertFalse(cr.canEnroll(s));
		s = sd.getStudentById("dnolan");
		assertFalse(cr.canEnroll(s));





		//Test valid student 
		Student s1 = new Student("new", "student", "nstudent", "nstudent@ncsu.edu", "pw", 18);
		assertTrue(cr.canEnroll(s1));
		try {
			cr.enroll(s1);				
		} catch (IllegalArgumentException e) {
			//invalid
		}
		assertEquals(0, cr.getOpenSeats());
				
	}

}
