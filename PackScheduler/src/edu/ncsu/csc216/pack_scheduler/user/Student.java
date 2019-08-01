package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class that stores information for Student objects.  Subclass of User.
 * Additional specific fields for Student includes: max credits.
 * Class includes two constructors, one with all six fields and one with five fields, excluding max credits.
 * Class includes getter and setter for max credits.
 * 
 * @author mlee25 Michael Lee
 */
public class Student extends User implements Comparable<Student> {
	
	/** Student's schedule */
	private Schedule schedule;
	
	/** Student's max credits */
	private int maxCredits;

	/** Constant for maxCredits upper limit */
	public static final int MAX_CREDITS = 18;
	
	/** Constructor for Student object with all fields.
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password Student's password
	 * @param maxCredits Student's max credits
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		this.schedule = new Schedule();
	}
	
	/** Constructor for Student object with five fields, excluding maxCredits
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password Student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
		this.schedule = new Schedule();
	}
	
	/**
	 * Returns the Student's max credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the Student's max credits
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if max credits is less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) throws IllegalArgumentException {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCredits;
	}

	/**
	 * hashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Equals method checks if two Student objects are equal and have the same max credits.
	 * Overrides method in User class.
	 * 
	 * @param obj the Student to compare
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Compares two students and returns a negative integer if this Student is less than the specified Student, returns 0 if the same, and a positive integer if this Student is greater than the specified Student.
	 * Comparisons are based on last name, then first name, and then student id.
	 * 
	 * @param s the Student to compare to this Student
	 * @return negative number if this Student is less than other Student, equals if state is equivalent and positive number if this is greater than other.
	 * @throws IllegalArgumentException if the Student to compare is null
	 */
	@Override
	public int compareTo(Student s) throws IllegalArgumentException {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (this.equals(s)) {
			return 0;
		}
		int result = this.getLastName().compareTo(s.getLastName());
		if (result == 0) {
			result = this.getFirstName().compareTo(s.getFirstName());
			if (result == 0) {
				result = this.getId().compareTo(s.getId());
			}
		}
		return result;
				
	}

	/**
	 * Getter for the Student's Schedule.
	 * @return the Schedule
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}

	/**
	 * Checks if a specified Course can be added to the Student's Schedule.
	 * Returns false if the Course is null, if the Course is already in the Schedule, if there is a conflict, or if the Student does not have sufficient room in their schedule based on credits.
	 * @param course the Course to check
	 * @return true if the Course can be added
	 */
	public boolean canAdd(Course course) {
		if (schedule.canAdd(course) && schedule.getScheduleCredits() + course.getCredits() <= maxCredits) {	
		return true;
		}
		return false;
	}

}
