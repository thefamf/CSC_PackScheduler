package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * ArrayList class for implementation of an array list that doesn’t allow for null elements or duplicate elements as defined by the equals() method.
 * @param <E> generic type parameter
 * @author mlee25 Michael Lee
 *
 */
public class ArrayList<E> extends AbstractList<E> {

	/** class constant for initializing the list size */
	private static final int INIT_SIZE = 10;
	/** array of type E */ 
	private E[] list;
	/** the size of the list */
	private int size;
	
	/**
	 * Constructor for ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * Adds an element to the list at the specified index and shifts subsequent elements to the right.
	 * If the size is equal to the capacity, the list doubles in capacity to make room for the new element. 
	 * If the element to add is null, throws a NullPointerException.
	 * If the element to add is a duplicate of an element already in the list, throws an IllegalArgumentException.
	 * If the index is out of range (index less than zero or greater than size), throws an IndexOutOfBoundsException.
	 * @param index the specified index 
	 * @param e the element to insert
	 */
	@Override
	public void add(int index, E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}	
		for (int i = 0; i < list.length; i++) {
			if (e.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		} 
		if (size == list.length) {
			growArray();
		}
		for (int i = list.length - 1; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = e;
		size++;
	}
	
	/**
	 * Removes the element at the specified index, returns the element removed, and shifts subsequent elements left.
	 * If the element to add is null, throws a NullPointerException.
	 * If the index is out of range (index less than zero or index greater than or equal to size), throws an IndexOutOfBoundsException.
	 * @param index the specified index to remove
	 * @return the element that was removed
	 */
	@Override
	public  E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E result = list[index];
		for (int i = index; i < size; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return result;
	}
	
	/**
	 * Sets an element at the specified index and returns the element that was replaced.
	 * If the element to set is null, throws a NullPointerException.
	 * If the element to add is a duplicate of an element already in the list, throws an IllegalArgumentException.
	 * If the index is out of range (index less than zero or index greater than or equal to size), throws an IndexOutOfBoundsException.
	 * @param index the specified index to set
	 * @param e the element to set at the index 
	 * @return the element that was removed
	 */
	@Override
	public  E set(int index, E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < list.length; i++) {
			if (e.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		} 
		E result = list[index];
		list[index] = e;
		return result;
	}
	
	/**
	 * Getter for the element at a specified index
	 * @param index the index of the element
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if index is less than zero or greater than or equal to size
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E result = list[index];
		return result;
	}
	
	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	@Override
	public int size() {
		int capacity = list.length;
		int result = 0;
		for (int i = 0; i < capacity; i++) {
			if (list[i] != null) {
				result++;
			}
		}
		return result;
	}

	/**
	 * Doubles the capacity of the list by replacing the list with a new list with double the capacity.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = (E[]) new Object[size * 2];
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		this.list = (E[]) new Object[temp.length];
		for (int i = 0; i < list.length; i++) {
			list[i] = temp[i];
		}	
	}

}
