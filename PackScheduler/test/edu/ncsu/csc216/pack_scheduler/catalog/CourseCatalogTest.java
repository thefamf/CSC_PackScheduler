package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class.
 * 
 * @author mlee25
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment capacity */
	private static final int ENROLLMENTCAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if unable to reset files
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);

	}
	
	/**
	 * Test newCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Add some courses and create a new catalog and check it is empty
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));
		assertTrue(cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445));
		assertEquals(2, cc.getCourseCatalog().length);
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Test loadCoursesFromFile().
	 */
	@Test
	public void loadCoursesFromFile() {
		//Test with invalid file.  Should have an empty catalog and schedule. 
		CourseCatalog cc1 = new CourseCatalog();
		assertEquals(0, cc1.getCourseCatalog().length);
		cc1.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, cc1.getCourseCatalog().length);
		
		//Test with valid file containing 8 courses.  Will test other methods in other tests.
		CourseCatalog cc2 = new CourseCatalog();
		cc2.loadCoursesFromFile(validTestFile);
		assertEquals(8, cc2.getCourseCatalog().length);
	}
	
	/**
	 * Test addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Attempt to add a course 
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));

		//Attempt to add a course that already exists
		assertFalse(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));

	} 
	
	/**
	 * Test removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Attempt to remove from empty schedule
		assertFalse(cc.removeCourseFromCatalog("CSC226", "001"));
		
		//Add some courses and remove them
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));
		assertTrue(cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445));
		assertTrue(cc.removeCourseFromCatalog("CSC116", "001"));
		assertTrue(cc.removeCourseFromCatalog("CSC216", "001"));
		
		//Check that removing all doesn't break future adds
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));

	}
	
	/**
	 * Test getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Attempt to get a course that doesn't exist
		assertNull(cc.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that exists
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME, END_TIME);
		assertTrue(cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445));
		assertEquals(c, cc.getCourseFromCatalog("CSC216", "001"));
	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(8, cc.getCourseCatalog().length);		
		
		//Get the catalog and make sure contents are correct
		//Name, section, title, meetingString
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
	}
	
	/**
	 * Test saveCourseCatalog().
	 */
	@Test
	public void saveCourseCatalog() {
		//Test that empty catalog exports correctly
		CourseCatalog cc = new CourseCatalog();
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Add courses and test that exports correctly
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));
		assertTrue(cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445));
		assertEquals(2, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_catalog_export.txt");
		checkFiles("test-files/expected_catalog_export.txt", "test-files/actual_catalog_export.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}