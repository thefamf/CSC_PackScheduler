package edu.ncsu.csc216.pack_scheduler.course;


/**
 * Activity class.  Superclass of Course and Event. Includes fields for Activity's title,
 * meeting days, starting time and ending time.
 * Includes methods for getters, setters, hashCode, and equals.
 * Includes methods to get string arrays for GUI.
 * @author mlee25 Michael Lee
 *
 */
public abstract class Activity implements Conflict {

	/** Constant containing the upper military time */
	public static final int UPPER_TIME = 2400;
	/** Constant containing the upper value for an hour */
	public static final int UPPER_HOUR = 60;
	
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Constructor for Activity with parameters for title, meeting days, start time and end time.
	 * @param title the title
	 * @param meetingDays the meeting days
	 * @param startTime the starting time
	 * @param endTime the ending time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		this.setTitle(title);
		this.setMeetingDays(meetingDays);
		this.setActivityTime(startTime, endTime);
	}
	
	/**
	 * Null constructor
	 */
	public Activity() {
		// this();
	}

	/**
	 * Returns the title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty String
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			// [E7]
			throw new IllegalArgumentException("Invalid course title");
		}
		this.title = title;
	}

	/**
	 * Returns the meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days
	 * 
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if meetingDays is null or empty String
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the start and end time
	 * 
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 * @throws IllegalArgumentException if startTime or endTime is less than zero or greater than
	 * 	2359 or an invalid military time, or if the endTime is less than the startTime
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < 0 || (startTime % 100) >= UPPER_HOUR || startTime >= UPPER_TIME) {
			throw new IllegalArgumentException("Invalid start time");
		}			
		if (endTime < 0 || (endTime % 100) >= UPPER_HOUR || endTime >= UPPER_TIME) {
			throw new IllegalArgumentException("Invalid end time");
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid course times");
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a hashCode
	 * 
	 * @return the hashCode for Activity
	 */	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Checks if two Activity objects are equal and have the same title, meeting days, start time, 
	 * end time
	 * 
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a String with meeting days, start time, and end time, or returns "Arranged" if meeting days is "A".
	 * Includes the functionality for converting military time representation to 12-hour time representation.
	 * 
	 * @return String representation of meeting days and times
	 */
	public String getMeetingString() {
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		String start = "";
		String end = "";
		String time = "";
		int startHours = 0;
		int endHours = 0;

		String startMinutes = startTime + "";
		String endMinutes = endTime + "";
		startMinutes = startMinutes.substring(startMinutes.length() - 2, startMinutes.length());
		endMinutes = endMinutes.substring(endMinutes.length() - 2, endMinutes.length());
		startHours = startTime / 100;
		endHours = endTime / 100;
	
		
		if (startHours == 0) {
			start = "12:" + startMinutes + "AM";
		}
		if (startHours == 12) {
			start = "12:" + startMinutes + "PM";
		}
		if (startHours > 12) {
			startHours -= 12;
			start = startHours + ":" + startMinutes + "PM";
		} else if (startHours < 12 && startHours != 0) {
			start = startHours + ":" + startMinutes + "AM";
		}

		if (endHours == 0) {
			end = "12:" + endMinutes + "AM";
		} 
		if (endHours == 12) {
			end = "12:" + endMinutes + "PM";
		}
		if (endHours > 12) {
			endHours -= 12;
			end = endHours + ":" + endMinutes + "PM";
		} else if (endHours < 12 && endHours != 0){
			end = endHours + ":" + endMinutes + "AM";
		}
		time = start + "-" + end;
		return meetingDays + " " + time;
	}

	/**
	 * Abstract method to provide short version of array for GUI
	 * @return the String[] array
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Abstract method to provide long version of array for GUI
	 * @return the String[] array
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Abstract method to check if Activity is duplicate
	 * @param activity the Activity to check if duplicate
	 * @return true if duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	
	/**
	 * Throws an exception if an Activity conflicts with this Activity by checking if there is an overlap of at least one day, and if there is an overlap in time, including by the same minute.
	 * @param possibleConflictingActivity the Activity to compare
	 * @throws ConflictException if the Activity conflicts with this Activity
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		char[] c = this.getMeetingDays().toCharArray();
		char[] other = possibleConflictingActivity.getMeetingDays().toCharArray();
		
		// check days
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < other.length; j++) {
				if (c[i] != 'A' && c[i] == other[j] && 
						this.getStartTime() <= possibleConflictingActivity.getEndTime() && this.getEndTime() >= possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
				}
			}
		}		
	}
}