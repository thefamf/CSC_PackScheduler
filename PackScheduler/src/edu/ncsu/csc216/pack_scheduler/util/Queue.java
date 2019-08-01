package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Interface for Queue with generic type.  
 * The queue adds elements to the back of the queue and removes elements from the front of the queue.
 * FIFO
 * @author mlee25 Michael Lee
 *
 * @param <E> generic type parameter
 */
public interface Queue<E> {

	/**
	 * Adds the given element to the back of the queue.
	 * @param element the element to add
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	void enqueue(E element) throws IllegalArgumentException;
	
	/**
	 * Removes and returns the element at the front of the queue.
	 * @return the element removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue() throws NoSuchElementException;
	
	/**
	 * Returns true if the queue is empty
	 * @return true if empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the queue.
	 * @return the number of elements
	 */
	int size();
	
	/**
	 * Sets the capacity for the queue.	
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if capacity is negative or less than the number of elements in the queue
	 */
	void setCapacity(int capacity) throws IllegalArgumentException;
}
