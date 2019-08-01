package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Interface for Stack with generic type.
 * The stack adds elements to the top and removes from the top.
 * LIFO
 * @author mlee25 Michael Lee
 *
 * @param <E> generic type parameter
 */
public interface Stack<E> {

	/** adds an element to the top of the stack
	 * 
	 * @param element the element to add
	 * @throws IllegalArgumentException if there is no room (capacity has been reached
	 */
	void push(E element) throws IllegalArgumentException;
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @return the element removed
	 * @throws EmptyStackException if the stack is empty
	 */
	E pop() throws EmptyStackException;
	
	/** Returns true if the stack is empty
	 * @return true if empty
	  */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack
	 * @return the number of elements
	 */
	int size();
	
	/**
	 * Sets the capacity for the stack
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if the parameter is negative or less than the number of elements in the stack
	 */
	void setCapacity(int capacity) throws IllegalArgumentException;
		
}
