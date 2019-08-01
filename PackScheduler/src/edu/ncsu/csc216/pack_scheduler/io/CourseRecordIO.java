package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author Michael Lee (mlee25)
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates of list of valid Courses.
	 * Any invalid Courses are ignored.
	 * If the file to read cannot be found or the permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		Course course = null;
		while (fileReader.hasNextLine()) {
			try {
				 course = readCourse(fileReader.nextLine());
			} catch (IllegalArgumentException e) {
				// skip the line
			}
			boolean duplicate = false;
			for (int i = 0; i < courses.size(); i++) {
				Course c = courses.get(i);
				if (course.getName().equals(c.getName()) && 
						course.getSection().equals(c.getSection())) {
					duplicate = true;
				}
			}
			if (!duplicate) {
				try {					
					courses.add(course);
				} catch (Exception e) {
					//invalid course
				}
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * Private helper method to process each Course.  
	 * @param nextLine the next line String to process 
	 * @return Course the Course object if the line is valid
	 */
	private static Course readCourse(String nextLine) {
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");

		Course result = null;
		String name = "";
		String title = "";
		String section = "";
		int credits = -1;
		String instructorId = "";
		int enrollmentCap = 0;
		String meetingDays = "";
		int startTime = 0;
		int endTime = 0;
		try {
		name = lineScanner.next();
		title = lineScanner.next();
		section = lineScanner.next();
			credits = lineScanner.nextInt();
		instructorId = lineScanner.next();
			enrollmentCap = lineScanner.nextInt();
		meetingDays = lineScanner.next();
		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			lineScanner.close();
			throw new IllegalArgumentException();				
		}
		if (meetingDays.equals("A") && lineScanner.hasNext()) { 
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		if (meetingDays.equals("A")) {
			result = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
		}
			startTime = lineScanner.nextInt();
			endTime = lineScanner.nextInt();
		result = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		} catch (Exception e) {
			//invalid line
		}
		lineScanner.close();
		return result;
	}

	/**
	 * Writes the given list of Courses to filename given as parameter
	 * @param fileName name of file to write
	 * @param courses SortedList of courses to write
	 * @throws IOException if file cannot be saved
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}
		fileWriter.close();		
	}

}