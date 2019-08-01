package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * LinkedAbstractList class for implementation of a linked-list version of an array list.
 * The list does not allow for null or duplicate elements as defined by the equals() method.
 * @param <E> generic type parameter
 * @author mlee25 Michael Lee
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** ListNode for the front of the list */
	private ListNode front;
	/** ListNode for the back of the list */
	//@SuppressWarnings("unused")
	private ListNode back;
	/** the size of the list */
	private int size;
	/** the capacity of the list */
	private int capacity;
	
	/**
	 * Constructor for LinkedAbstractList.  Sets the capacity to the specified parameter.
	 * @param c capacity to set for new LinkedAbstractList
	 * @throws IllegalArgumentException if c is less than zero or greater than size
	 */
	public LinkedAbstractList(int c) throws IllegalArgumentException {
		size = 0;
		capacity = 0;
		if (c < 0 || c < this.size) {
			throw new IllegalArgumentException("invalid construction");
		}
		front = null;
		back = front;
		setCapacity(c);
	}
	
	/**
	 * Adds an element to the list at the specified position and updates the next references of the list.
	 * Elements cannot be added to the list once the capacity is reached. 
	 * If the element to add is null, throws a NullPointerException.
	 * If the element to add is a duplicate of an element already in the list, throws an IllegalArgumentException.
	 * If the position is out of range (index less than zero or greater than size), throws an IndexOutOfBoundsException.
	 * @param position the specified index 
	 * @param e the element to insert
	 * @throws IndexOutOfBoundsException if position is less than 0 or greater than size
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if size is equal to capacity, or if the element to add is a duplicate
	 */
	@Override
	public void add(int position, E e) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("invalid position");
		}	
		if (e == null) {
			throw new NullPointerException();
		}
		if (size() == getCapacity()) {
			throw new IllegalArgumentException("exceeds capacity");
		}
		for (ListNode p = front; p != null; p = p.next) {
			if (e.equals(p.data)) {
				throw new IllegalArgumentException("duplicate");
			}
		}
		if (position == 0) {
			front = new ListNode(e, front);
			if (size == 0) {
				back = front.next;
			}
			size++;
		} else if (front != null && position > 0) {
			ListNode current = front;
			while (current != null && position > 1) {
				current = current.next;
				position--;
			}
			if (current != null) {				
				current.next = new ListNode(e, current.next);
				size++;
			}
		}
//		if (this.size == this.capacity) {
//			throw new IllegalArgumentException("size equals capacity");
//		}
	}
	
	/**
	 * Removes the element at the specified index, returns the element removed, and updates next references.
	 * If the index is out of range (index less than zero or index greater than or equal to size), throws an IndexOutOfBoundsException.
	 * @param index the specified index to remove
	 * @return the element that was removed
	 * @throws IndexOutOfBoundsException if index is less than zero, or greater than or equal to size
	 */
	@Override
	public  E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E result = null;
		ListNode current = front;
		ListNode previous = null;
		// re-assign back if last element is removed
//		if (index == size - 1) {
//			int last = index;
//			while (current != null && last > 0) {
//				previous = current;
//				current = current.next;
//				last--;
//			}
//			back = current;
//		}

		if (index == 0) {
			result = front.data;
		}
		while (current != null && index > 0) {
			previous = current;
			current = current.next;
			index--;
		}
		if (current != null) {
			if (current == front) {
				front = front.next;
				back = front;
			} else {
				previous.next = current.next;
				result = current.data;
			}
		}
		size--;
		return result;
	}
	
	/**
	 * Sets an element at the specified index and returns the element that was replaced.
	 * If the element to set is null, throws a NullPointerException.
	 * If the element to add is a duplicate of an element already in the list, throws an IllegalArgumentException.
	 * If the index is out of range (index less than zero, or greater than or equal to size), throws an IndexOutOfBoundsException.
	 * @param index the specified index to set
	 * @param e the element to set at the index 
	 * @return the element that was removed
	 * @throws NullPointerException if e is null
	 * @throws IllegalArgumentException if e is a duplicate
	 * @throws IndexOutOfBoundsException if index is less than zero, or greater than or equal to size
	 */
	@Override
	public  E set(int index, E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (ListNode p = front; p != null; p = p.next) {
			if (e.equals(p.data)) {
				throw new IllegalArgumentException();
			}
		}
		E result = null;
		if (front != null && index == 0) {
			result = front.data;
			front.data = e;
			return result;
		} 

		ListNode current = front;
		while (index > 0) {
			current = current.next;
			index--;
		}			
		//check not beyond end of list
		if (current != null) {				
			result = current.data;
			current.data = e;
		}
		return result;
	}
	
	/**
	 * Getter for the element at a specified position in the list
	 * @param index the position of the element (0 equals front)
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if index is less than zero or greater than or equal to size
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E result = null;
		if (front != null && index == 0) {
			return front.data;
		} 
		ListNode current = front;
		while (index > 0) {
			current = current.next;
			index--;
			}			
		//check not beyond end of list
			if (current != null) {				
				result = current.data;
			}
		return result;
	}
	
	/**
	 * Getter for back ListNode
	 * @return the back ListNode
	 * @throws IllegalArgumentException if the capacity parameter is less than zero or less than the current size
	 */
	public E getBack() throws IllegalArgumentException {
		E result = null;
		result = get(size - 1);
		return result;
	}
	

	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Getter for capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Setter for capacity
	 * @param c the capacity to set
	 * @throws IllegalArgumentException if the capacity parameter is less than zero or less than the current size
	 */
	public void setCapacity(int c) throws IllegalArgumentException {
		if (c < 0 || c < this.size) {
			throw new IllegalArgumentException("invalid capacity");
		}
		capacity = c;
	}

	/**
	 * Inner class for ListNode.  
	 * Each ListNode contains two private fields: a data element and a reference to the next node.
	 */
	private class ListNode {
		/** Data element */
		public E data;
		/** Next node */
		public ListNode next;

		/**
		 * Constructor for ListNode with one parameter.  Assigns the data element and initializes next to null;
		 * @param data the data element
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * Constructor for ListNode with two parameters.
		 * Assigns data field and creates a new null ListNode for next field;
		 * @param data the data element
		 * @param next the next element
		 */
		public ListNode(E data, ListNode next) {
			//this.data = data;
			this(data);
			this.next = next;
		}
	}
}
