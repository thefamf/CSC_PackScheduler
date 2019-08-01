package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Concrete class to implement a stack as an array list.
 * @param <E> generic type parameter
 * @author mlee25 Michael Lee
 *
 */
public class ArrayStack<E> implements Stack<E> {

	/** array list of type E */ 
	private ArrayList<E> stack;
	/** the capacity of the stack */
	private int capacity;
	
	/**
	 * Constructor for ArrayStack.
	 * @param capacity the initial capacity
	 */
	public ArrayStack(int capacity) {
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void push(E element) throws IllegalArgumentException {
		if (stack.size() == capacity) {
			throw new IllegalArgumentException("beyond capacity");
		}
		stack.add(stack.size(), element);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		E result = null;
		result = (E) stack.remove(stack.size() - 1);
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0 || capacity < this.size()) {
			throw new IllegalArgumentException("invalid capacity");
		}
		this.capacity = capacity;
	}

}
