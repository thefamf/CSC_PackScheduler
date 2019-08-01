package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager class handles user authentication and maintains a single instance of RegistrationManager, CourseCatalog and StudentDirectory to control interaction with the GUI.
 * Uses a Properties file to read in name-value pairs for user information and handles hashing for passwords.
 * Contains the Registrar inner class to represent a Registrar User to be isolated from Student class.
 * Uses the singleton design pattern.
 * @author mlee25 Michael Lee
 *
 */
public class RegistrationManager {
	
	/** private instance variable for RegistrationManager */
	private static RegistrationManager instance;
	/** instance variable for CourseCatalog */
	private CourseCatalog courseCatalog;	
	/** instance variable for StudentDirectory */
	private StudentDirectory studentDirectory;
	/** instance variable for a registrar User */
	private User registrar;
	/** instance variable for a student User */
	private User currentUser;
	/** Constant for the hash algorithm */
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Parameterless constructor for RegistrationManager.  Initializes courseCatalog and studentDirectory.
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		currentUser = null;
	}
	
	/**
	 * Creates a new registrar from the properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Method to hash the password.
	 * @param pw the password
	 * @return the hashed password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Getter for the single instance of RegistrationManager.  Creates a new single instance if one does not exist.
	 * Method is static and belongs to the class.
	 * @return the instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Getter for the single instance of CourseCatalog.
	 * @return the CourseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Getter for the single instance of StudentDirectory.
	 * @return the studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Handles user authentication.  The provided password is hashed using the SHA-256 algorithm and compared to the user�s stored password. 
	 * Upon successful login, sets the current user to the user matching the id.
	 * If the user doesn�t exist in the system, an IllegalArgumentException is thrown with the message �User doesn�t exist�. 
	 * If the user�s hashed password doesn�t match the stored hashed password, a pop-up message stating �Invalid id or password� is displayed. The user clicks OK and is returned to the authentication window [UC0].
	 * @param id the user's id
	 * @param password the User's password (in plain text)
	 * @return true if the password matches the stored password
	 * @throws IllegalArgumentException if the password cannot be hashed, the user does not exist, or the password is not correct
	 */
	public boolean login(String id, String password) {
		// Check that another User is not already logged in
		if (currentUser != null) {
			return false;
		}
		
		String localHashPW = null;
		try {
		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			localHashPW = new String(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}
		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			} else {
				throw new IllegalArgumentException("Invalid id or password");
				// return false;
			} 
		}
		if (studentDirectory != null) {
			try {
				Student s = studentDirectory.getStudentById(id);
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				} else {
					throw new IllegalArgumentException("Invalid id or password");
				}
			} catch (NullPointerException e) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}
		return false;
	}

	/**
	 * Logs out the current user and sets currentUser to null.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Getter for currentUser.
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Creates a new empty CourseCatalog and StudentDirectory
	 */
	public void clearData() {
		if(courseCatalog != null) {
			courseCatalog.newCourseCatalog();
		}
		if(studentDirectory != null) {
			studentDirectory.newStudentDirectory();
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Inner class for Registrar, a child class of User. 
	 * @author mlee25 Michael Lee
	 *
	 */
	private static class Registrar extends User {
		
		/**
		 * Constructor for a Registrar User with the user id and password in the registrar.properties file.
		 * @param firstName the first name
		 * @param lastName the last name
		 * @param id the id
		 * @param email the email
		 * @param hashPW the password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}