package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentRecordIO.
 * @author mlee25 Michael Lee
 */
public class StudentRecordIOTest {

	/** Valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid student records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	
	/** Expected results for valid students */
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	@SuppressWarnings("javadoc")
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	@SuppressWarnings("javadoc")
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	@SuppressWarnings("javadoc")
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	@SuppressWarnings("javadoc")
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	@SuppressWarnings("javadoc")
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	@SuppressWarnings("javadoc")
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	@SuppressWarnings("javadoc")
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	@SuppressWarnings("javadoc")
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	@SuppressWarnings("javadoc")
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";

	/** Array to hold expected results */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};

	/** Variables for password and hash conversion for setUp */
	private String hashPW;
	/** Constant for hash algortithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Replaces "pw" string in student records with hashed value for comparison.
	 * @throws Exception if unable to create hash
	 */
	@Before
	public void setUp() throws Exception {
		 try {
		        String password = "pw";
		        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		        digest.update(password.getBytes());
		        hashPW = new String(digest.digest());
		        
		        for (int i = 0; i < validStudents.length; i++) {
		            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
		        }
		    } catch (NoSuchAlgorithmException e) {
		        fail("Unable to create hash during setup");
		    }
	}
	
	/**
	 * Tests readStudentRecords().
	 * @throws FileNotFoundException if file cannot be read
	 */
	@Test
	public void testReadStudentRecords() throws FileNotFoundException {
		// tests validTestFile
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}

		// Tests invalidTestFile
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
			
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}

		// Tests file that does not exist
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/nonexistentfile.txt");
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			// thrown from readStudentRecords()
		}
	}

	/**
	 * Tests writeStudentRecords()
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");		
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
	
	/**
	 * Tests writeStudentRecords to a destination that is not permitted
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	}
}
