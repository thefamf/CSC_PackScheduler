package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Class to handle the course roll for a given Course and keep track of enrolled Students and add/remove Students from the Course.
 * Each CourseRoll knows what Course it is keeping track of for its roll and its waitlist.
 * @author mlee25 Michael Lee
 *
 */
public class CourseRoll {

	/** LinkedAbstractList of Student objects */
	private LinkedAbstractList<Student> roll;
	/** LinkedQueue of Students for the waitlist */
	private LinkedQueue<Student> waitlist;
	/** the roll's enrollment capacity */
	private int enrollmentCap;
	/** Constant for the minimum enrollment */
	private static final int MIN_ENROLLMENT = 10;
	/** Constant for the maximum enrollment */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Constructor for CourseRoll.  
	 * Creates an empty LinkedAbstractList of Students. 
	 * @param course the Course associated with this CourseRoll
	 * @param x the initial enrollment capacity
	 * @throws IllegalArgumentException if enrollmentCap is less than MIN_ENROLLMENT or greater than MAX_ENROLLMENT. 
	 */
	public CourseRoll(Course course, int x) {
		if (course == null) {
			throw new IllegalArgumentException();
		}
		this.roll = new LinkedAbstractList<Student>(0);
		this.setEnrollmentCap(x);
		waitlist = new LinkedQueue<Student>(10);
	}
	
	/**
	 * Getter for enrollmentCap.
	 * @return the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	/**
	 * Setter for enrollmentCap. enrollmentCap can be changed for an existing CourseRoll if the new enrollmentCap is not less than the number of currently enrolled students.
	 * @param x the enrollmentCap to set
	 * @throws IllegalArgumentException if enrollmentCap is less than MIN_ENROLLMENT or greater than MAX_ENROLLMENT
	 */
	public void setEnrollmentCap(int x) throws IllegalArgumentException {
		if (x < MIN_ENROLLMENT || x > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("invalid enrollmentCap");
		}
		roll.setCapacity(x);
		enrollmentCap = x;
	}

	/**
	 * Enroll a Student in the Course and adds them to the end of the CourseRoll list.
	 * If the courseroll is at capacity, the Student is added to the waitlist.
	 * If the waitlist is full, the Student cannot enroll and an IllegalArgumentException is thrown
	 * @param s the Student to enroll
	 * @throws IllegalArgumentException if Student is null, there is no more room on the waitlist, the Student is already enrolled, or if adding the Student to the LinkedAbstractList generates an exception
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("enroll null student");
		}
		if (!this.canEnroll(s) && this.getNumberOnWaitlist() >= 10){
			throw new IllegalArgumentException("waitlist full");
		}
		if (!this.canEnroll(s)) {
			throw new IllegalArgumentException("duplicate student");
		}
		if (this.canEnroll(s)) {
			if (this.getOpenSeats() > 0) {
				this.roll.add(roll.size(), s);
			} else {
				try {
					waitlist.enqueue(s);
				} catch (Exception e) {
					throw new IllegalArgumentException("Exception enqueue");					
				}
			}
		}

	}
	
	/**
	 * If a Student is on the main CourseRoll list, removes the Student from the CourseRoll, and the student at the front of the waitlist queue is added to the CourseRoll.
	 * If a Student is on the waitlist queue, removes the Student from the waitlist.
	 * @param s the Student to drop
	 * @throws IllegalArgumentException if Student is null or if removing the Student from the LinkedAbstractList generates an exception
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		// check if student on waiting list and rebuild the waiting list
		Student dq = null;
		if (this.getNumberOnWaitlist() > 0) {
			for (int i = 0; i < this.getNumberOnWaitlist(); i++) {
				dq = waitlist.dequeue();
				if (dq.getId() != s.getId()) {
					waitlist.enqueue(dq);
				}
			}
		}

		// check if student on main roll and remove
		for (int i = 0; i < this.roll.size(); i++) {
			if (this.roll.get(i).getId() == s.getId()) {
				try {
					this.roll.remove(i);
				} catch (Exception e) {
					throw new IllegalArgumentException();
				}
				// check if waiting list has students and add front student to main roll
				if (this.getNumberOnWaitlist() > 0) {
					Student sWaiting = this.waitlist.dequeue();
					try {
						this.roll.add(roll.size(), sWaiting);
					} catch (Exception e) {
						throw new IllegalArgumentException();
					}
				}
			}
		}
	}
	
	
	/**
	 * Getter for the number of open seats.
	 * @return the difference between enrollmentCap and the current size of the CourseRoll roll
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Getter for the number of students on the waitlist.
	 * @return the current size of the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	/**
	 * Method to check if a Student can be enrolled in a Course.  
	 * @param student the Student to check
	 * @return true if the Student can be added or false if there is no more room in the Course or the Student is already enrolled.
	 */
	public boolean canEnroll(Student student) {
		boolean result = false;
		boolean duplicate = false;
		//check duplicate
		for (int i = 0; i < this.roll.size(); i++) {
			Student s = this.roll.get(i);
			if (s.equals(student)) {
//				duplicate = true;
				return false;
			}
		}
		int waitsize = this.getNumberOnWaitlist();
		for (int i = 0; i < waitsize; i++) {
			Student dq = waitlist.dequeue();
			waitlist.enqueue(dq);
			if (dq.equals(student)) {
//				duplicate = true;
				return false;
			}
		}
		//check capacity of roll
		if (this.getOpenSeats() > 0 && !duplicate) {
			result = true;
		}
		//check capacity of waitlist
		if (this.waitlist.size() < 10 && !duplicate) {
			result = true;
		}

		return result;
	}
}
