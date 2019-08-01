package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Concrete class for a generic type queue using a linked list.  
 * The queue adds elements to the back of the queue and removes elements from the front of the queue.
 * @author mlee25 Michael Lee
 *
 * @param <E> generic type parameter
 */
public class LinkedQueue<E> implements Queue<E> {

	/** LinkedAbstractList of type E */
	private LinkedAbstractList<E> queue;

	/**
	 * Constructor for LinkedQueue.
	 * @param capacity the initial capacity
	 */
	public LinkedQueue(int capacity) {
		queue = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds the given element to the back of the queue.
	 * @param element the element to add
	 * @throws IllegalArgumentException if the capacity has been reached
	 */
	@Override
	public void enqueue(E element) throws IllegalArgumentException {
		if (queue.size() == queue.getCapacity()) {
			throw new IllegalArgumentException("beyond capacity");
		}
		queue.add(queue.size(), element);	
	}

	/**
	 * Removes and returns the element at the front of the queue.
	 * @return the element removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() throws NoSuchElementException {
		if (queue.isEmpty()) {
			throw new NoSuchElementException();
		}
		E result = null;
		result = queue.remove(0);
		return result;
	}

	/**
	 * Returns true if the queue is empty
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue.
	 * @return the number of elements
	 */
	@Override
	public int size() {
		return queue.size();
	}

	/**
	 * Sets the capacity for the queue.	
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if capacity is negative or less than the number of elements in the queue
	 */
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0 || capacity < this.size()) {
			throw new IllegalArgumentException("invalid capacity");
		}
		queue.setCapacity(capacity);
	}

}
