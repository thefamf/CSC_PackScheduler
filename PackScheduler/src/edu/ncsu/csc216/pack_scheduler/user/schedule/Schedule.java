package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule class that manages a student's schedule as a custom ArrayList of Courses.
 * Handles creating a schedule and its Title, adding and removing Courses and returning a 2D array to interface with the GUI.
 * @author mlee25 Michael Lee
 */
public class Schedule {

	/** custom ArrayList of Courses */
	private ArrayList<Course> schedule;
	
	/** the schedule's title */
	private String title;
	
	/**
	 * Constructor for Schedule.  Initializes schedule and title instance variables.
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}

	/**
	 * Adds the specified course to the end of the schedule and return true if the Course was added.
	 * If the Course is a duplicate to with an existing Course in the schedule, an IllegalArgumentException is thrown.
	 * If there is a conflict with an existing Course in the schedule, an IllegalArgumentException is thrown.
	 * @param course the Course to add
	 * @return true if the Course is added
	 * @throws IllegalArgumentException if the course is a duplicate or conflicts with an existing course in the schedule
	 */
	public boolean addCourseToSchedule(Course course) throws IllegalArgumentException {
		Course c = null;
		int size = this.schedule.size();
		for (int i = 0; i < this.schedule.size(); i++) {
			c = (Course) this.schedule.get(i);
			if (course.isDuplicate(c) || course.equals(c)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			try {
				course.checkConflict(c);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		this.schedule.add(size, course);
		return true;
	}
	
	/**
	 * Removes the specified course from the schedule and returns true if the Course was removed and false if there was not a Course to remove..
	 * @param course the Course to remove
	 * @return true if the Course was removed and false if there was not a Course to remove
	 */
	public boolean removeCourseFromSchedule(Course course) {
		boolean result = false;
		Course c = null;
		try {
			for (int i = 0; i < this.schedule.size(); i++) {
				c = (Course) this.schedule.get(i);
				if (course.equals(c)) {
					this.schedule.remove(i);
					result = true;
				}
			}
		} catch (NullPointerException e) {
			// NullPointerException if course is null
		}
		return result;
	}
	
	/**
	 * Creates a new schedule ArrayList and reset the title to the default value.
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	/**
	 * Getter for 2D array of Courses. 
	 * Each row is a Course and the columns are name, section, title, the meeting string, and open seats via Course.getShortDisplayArray().
	 * @return the 2D array
	 */
	public String[][] getScheduledCourses() {
		Course c = null;
		int size = this.schedule.size();
		String[][] result = new String[size][5];
		for (int i = 0; i < size; i++) {
			c = (Course) this.schedule.get(i);
			result[i] = c.getShortDisplayArray();
		}
		return result;
	}
	
	/**
	 * Sets the title of the schedule.
	 * If the title is null, an IllegalArgumentException is thrown with the messages �Title cannot be null."
	 * @param title the title
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Getter for the title of the schedule.
	 * @return the title of the schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Getter for schedule credits.
	 * @return the number of credits
	 */
	public int getScheduleCredits() {
		int result = 0;
		for (int i = 0; i < schedule.size(); i++) {
			result += schedule.get(i).getCredits();
		}
		return result;
	}
	
	/**
	 * Checks if the specified Course can be added to this Schedule.  
	 * Returns false if the Course is null or if the Course is already in the schedule or if there is a conflict.
	 * @param course the Course to check
	 * @return true if the Course can be added
	 * 
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			try {
				c.checkConflict(course);
			} catch (ConflictException e) {
				return false;
			}
			if (c.isDuplicate(course)) {
				return false;
			}
		}
		return true;
	}
}
