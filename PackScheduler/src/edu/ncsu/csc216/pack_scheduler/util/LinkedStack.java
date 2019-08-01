package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Concrete class to implement a stack as a linked list.
 * @param <E> generic type parameter
 * @author mlee25 Michael Lee
 *
 */
public class LinkedStack<E> implements Stack<E> {

	/** LinkedAbstractList of type E */
	private LinkedAbstractList<E> stack;
	
	/**
	 * Constructor for LinkedStack with a given capacity
	 * @param capacity the capacity
	 */
	public LinkedStack(int capacity) { 
		stack = new LinkedAbstractList<E>(capacity);
	}
	
	@Override
	public void push(E element) throws IllegalArgumentException {
		if (stack.size() == stack.getCapacity()) {
			throw new IllegalArgumentException("beyond capacity");
		}
		stack.add(stack.size(), element);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (stack.size() == 0) {
			throw new EmptyStackException();
		}
		E result = (E) stack.remove(stack.size() - 1);
		return result;
	}

	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		stack.setCapacity(capacity);
	}

}
