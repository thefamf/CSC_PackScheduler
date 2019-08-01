/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class that stores information for Course objects, including name, section, credit hours,
 * and instructor.
 * Includes two Constructors and methods for getters, setters, equals, hash, and toString methods. 
 * @author Michael Lee (mlee25)
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** Constant containing the length of the section String */
	public static final int SECTION_LENGTH = 3;
	/** Constant containing the maximum length of the name String */
	public static final int MAX_NAME_LENGTH = 8;
	/** Constant containing the minimum length of the name String */
	public static final int MIN_NAME_LENGTH = 4;
	/** Constant containing the maximum number of credits for a course */
	public static final int MAX_CREDITS = 5;
	/** Constant containing the minimum number of credits for a course */
	public static final int MIN_CREDITS = 1;
	/** Course name. */
	private String name;
	/** Course section. */
	private String section;
	/** Course credit hours */
	private int credits;
	/** Course instructor */
	private String instructorId;
	/** Course Name Validator */
	private CourseNameValidator validator;
	/** Course roll */
	private CourseRoll roll;
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * meetingDays, startTime, and endTime.
	 * 
	 * @param name         Course name
	 * @param title        Course title
	 * @param section      Course section
	 * @param credits      Course credit hours
	 * @param instructorId Course instructor
	 * @param enrollmentCap Enrollment capacity
	 * @param meetingDays  Course meeting days as series of chars
	 * @param startTime    Course starting time
	 * @param endTime      Course ending time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super.setTitle(title);
		super.setMeetingDays(meetingDays);
		super.setActivityTime(startTime, endTime);
		validator = new CourseNameValidator();
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged, with startTime and endTime set to zero.
	 * 
	 * @param name         Course name
	 * @param title        Course title
	 * @param section      Course section
	 * @param credits      Course credit hours
	 * @param instructorId Course instructor
	 * @param enrollmentCap Enrollment capacity
	 * @param meetingDays  Course meeting days as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. 
	 * If the name is null, has a length less than 4 or greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or empty or length is less than 4 or 
	 * greater than 8
	 */
	private void setName(String name) {
		if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name");
		}
		try {
			if (validator.isValid(name)) {
				this.name = name;			
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name");
		}
	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null or length is not equal to 3
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section number");
		}
		char a = section.charAt(0);
		char b = section.charAt(1);
		char c = section.charAt(2);
		if (Character.isDigit(a) && Character.isDigit(b) && Character.isDigit(c)) {
			this.section = section;
		}
	}

	/**
	 * Returns the Course's credit hours
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credit hours
	 * 
	 * @param credits the number of credit hours to set
	 * @throws IllegalArgumentException if credits is less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is null or empty String
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException("Invalid instructor unity id");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns the CourseRoll
	 * 
	 * @return the roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * Returns a hashCode
	 * 
	 * @return the hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Checks if two Course objects are equal and have the same name, section, credits, and
	 * instructor id
	 * 
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
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
/*  		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else  */
 		if (!instructorId.equals(other.instructorId))
			return false;
/*		if (name == null) {
			if (other.name != null)
				return false;
		} else  */
		if (!name.equals(other.name))
			return false;
/*		if (section == null) {
			if (other.section != null)
				return false;
		} else  */
		if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (this.getMeetingDays().equals("A")) {
			return name + "," + this.getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + this.getMeetingDays();
		}
		return name + "," + this.getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + this.getMeetingDays() + ","
				+ this.getStartTime() + "," + this.getEndTime();
	}

	/**
	 * Returns a String array for the short representation of the Course for the GUI,
	 * including name, section, title, the meeting string, and open seats.
	 * 
	 * @return String array for the short display representation of the Course
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] result = new String[5];
		result[0] = this.name;
		result[1] = this.section;
		result[2] = this.getTitle();
		result[3] = this.getMeetingString();
		result[4] = String.valueOf(this.roll.getOpenSeats());
		return result;
	}

	/**
	 * Returns a String array for the long representation of the Course for the GUI,
	 * including name, section, title, credits, instructorId, the meeting string and an empty String.
	 * 
	 * @return String array for the long display representation of the Course
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] result = new String[7];
		result[0] = this.name;
		result[1] = this.section;
		result[2] = this.getTitle();
		result[3] = Integer.toString(this.credits);
		result[4] = this.getInstructorId();
		result[5] = this.getMeetingString();
		result[6] = "";		
		return result;
	}

	/**
	 * Sets the meetingDays for Course objects, which can include the weekdays or 'A' for arranged.
	 * 
	 * @param meetingDays the meeting days to set
	 * @throws IllegalArgumentException if meeting days include any characters other than
	 * 'M', 'T', 'W', 'H', 'F', or 'A', 
	 * or if the meeting days are 'A' and include any other characters
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		char[] c = meetingDays.toCharArray();
		int invalid = 0;
		for (int i = 0; i < meetingDays.length(); i++) {
			if (c[i] != 'M' && c[i] != 'T' && c[i] != 'W' && c[i] != 'H' && c[i] != 'F' && c[i] != 'A') {
				invalid++;
			}
		}
		if (invalid > 0 || meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException();
		} 
		super.setMeetingDays(meetingDays);
		
		
	}
	
	/**
	 * Checks if the given Activity is a duplicate of an existing Course in the schedule.
	 * The test checks if the Activity is not null and is a Course object, and if it has the same name as this Course.
	 * @param activity the Activity to check if duplicate
	 * @return true if duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		boolean result = false;
		Course other = null;
		if (activity instanceof Course) {
			other = (Course) activity;		
			if (this.name.equals(other.getName())) {
			result = true;			
			}
		}
		return result;	
	}

	/**
	 * Compares two Courses and returns a negative integer if this Course is less than the specified Course, returns 0 if the same, and a positive integer if this Course is greater than the specified Course.
	 * Comparisons are based on course name, then section.
	 * @param c the Course to compare to this Course
	 * @throws IllegalArgumentException if the Course to compare is null
	 */
	@Override
	public int compareTo(Course c) throws IllegalArgumentException {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		if (this.equals(c)) {
			return 0;
		}
		int result = this.getName().compareTo(c.getName());
		if (result == 0) {
			result = this.getName().compareTo(c.getName());
			if (result == 0) {
				result = this.getSection().compareTo(c.getSection());
			}
		}
		return result;				
	}
}