package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Abstract class User.  Superclass of Registrar and Stduent.  Includes fields for first name, last name, id, email and password.
 * Includes methods for getters, setters, hashCode, and equals.
 * Setter for id is private because it serves as each user's unique identifier and should not be modified after construction or by any other classes.
 * @author mlee25 Michael Lee
 */
public abstract class User {

	/** first name */
	private String firstName;
	/** last name */
	private String lastName;
	/** id */
	private String id;
	/** email */
	private String email;
	/** password */
	private String password;

	/**
	 * Constructor for User.  Takes all five fields including: first name, last name, id, email and password.
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password Student's password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}	
	
	/**
	 * Returns the first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty string
	 */
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty string
	 */
	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.  Unique identifier in StudentDirectory and private.  
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty string
	 */
	protected void setId(String id) throws IllegalArgumentException {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is null or empty string or does not contain a '.' or '@' or if the last '.' precedes the '@'
	 */
	public void setEmail(String email) throws IllegalArgumentException {
		if (email == null || email.equals("") || !email.contains(".") || !email.contains("@") || email.indexOf('.', email.indexOf('@')) < 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the password as a hashed String
	 * @return the hashPW
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password
	 * @param password the hashPW to set
	 * @throws IllegalArgumentException if password is null or empty string
	 */
	public void setPassword(String password) throws IllegalArgumentException {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * hashCode method.
	 * @return the hashcode value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Equals method compares this User to other User by comparing first name, last name, id, email and password (as a hashed value).
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}