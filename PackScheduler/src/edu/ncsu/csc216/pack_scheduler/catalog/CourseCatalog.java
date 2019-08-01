package edu.ncsu.csc216.pack_scheduler.catalog;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Maintains a catalog of all courses available at NC State as a sorted list of courses.
 * Includes methods to read courses from a file, add and remove courses, and export the catalog.
 * @author Michael Lee (mlee25)
 */
public class CourseCatalog {
	//test0713
	/** List of courses in the catalog */
	private SortedList<Course> courseCatalog;
	
	/**
	 * Constructs an empty course catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Constructs an empty course catalog as a Sorted List. 
	 */
	public void newCourseCatalog() {
		courseCatalog = new SortedList<Course>();
	}
	
	/**
	 * Loads course records into the catalog from the filename parameter.  
	 * 
	 * @param fileName file containing list of Courses
	 * @throws IllegalArgumentException if file not found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a Course to the catalog.  Returns true if the course is added and false if
	 * the course is unable to be added because the name/section pair matches another course in the catalog.
	 * 
	 * @param name         Course name
	 * @param title        Course title
	 * @param section      Course section
	 * @param credits      Course credit hours
	 * @param instructorId Course instructor
	 * @param enrollmentCap Course enrollment capacity
	 * @param meetingDays  Course meeting days as series of chars
	 * @param startTime    Course starting time
	 * @param endTime      Course ending time
	 * @return true if added
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
				return false;
				//throw new IllegalArgumentException("Course already in system.");
			}
		}
		courseCatalog.add(course);
		return true;
	}
	
	/**
	 * Removes the course with the given name and section from the list of courses.
	 * Returns true if the course is removed and false if the course is not in the list.
	 * @param name course name
	 * @param section course section
	 * @return true if removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				courseCatalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a Course that matches the provided name and section
	 * @param name course name
	 * @param section course section
	 * @return Course matching name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course result = null;
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				result = c;
			}
		}
		return result;
	}

	/**
	 * Returns all courses in the catalog with a column for name and section.
	 * @return String array containing course name and section.
	 */
	public String[][] getCourseCatalog() {
		String [][] catalog = new String[courseCatalog.size()][5];
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			catalog[i][0] = c.getName();
			catalog[i][1] = c.getSection();
			catalog[i][2] = c.getTitle();
			catalog[i][3] = c.getMeetingString();
			catalog[i][4] = String.valueOf(c.getCourseRoll().getOpenSeats());
		}
		return catalog;
	}
	
	/**
	 * Saves all courses in the catalog to a file.
	 * @param fileName name of file to save courses to.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, courseCatalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}


}
