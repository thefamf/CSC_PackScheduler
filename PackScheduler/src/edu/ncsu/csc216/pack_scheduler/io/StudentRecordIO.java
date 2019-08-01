package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Reads and writes student records to/from a file.
 * CSC 216 Lab 01
 * @author mlee25 Michael Lee
 *
 */
public class StudentRecordIO {

	/**
	 * Reads student records from a file, skipping any lines where student has duplicate id
	 * @param fileName file to read for Student records
	 * @return a list of valid Students
	 * @throws FileNotFoundException if file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();

		while (fileReader.hasNextLine()) {
			try {
				Student student = readStudent(fileReader.nextLine());
				students.add(student);
			} catch (IllegalArgumentException e) {
				// skip line
			}
		}
		
		fileReader.close();
		return students;
		}

	/**
	 * Writes the given list of Students to filename given as parameter
	 * @param fileName the file to be written
	 * @param studentDirectory ArrayList of Students to be written
	 * @throws IOException if file cannot be saved
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
	}

	/**
	 * Private helper method to process each Student.
	 * @param nextLine the line to be read from the file
	 * @return Student object
	 */
	private static Student readStudent(String nextLine) {
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");
		Student student = null;
		String firstName = "";
		String lastName = "";
		String id = "";
		String email = "";
		String hashPW = "";
		int maxCredits = 0;
		
		try {
			firstName = lineScanner.next();
			lastName = lineScanner.next();
			id = lineScanner.next();
			email = lineScanner.next();
			hashPW = lineScanner.next();
			maxCredits = lineScanner.nextInt();			
		} catch (NoSuchElementException e) {
			// treat line as invalid and skip
		}
		student = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		lineScanner.close();
		return student;
	}
}
