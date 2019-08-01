package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for SortedList
 * @author mlee25 Michael Lee
 *
 */
public class SortedListTest {
	
	/**
	 * Tests SortedList() constructor by testing that SortedList object is constructed correctly and the list is empty when newly constructed.
	 * Tests that SortedList object will grow correctly, beyond the initial capacity of 10 elements, by adding 11 elements to an empty list.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		// Test that the list grows by adding at least 11 elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		assertEquals(11, list.size());				
	}

	/**
	 * Tests add() method to confirm elements are added correctly, and when add() is used outside of the constructor.  
	 * Tests for adding elements to the front, back, and middle of the list, adding a null element (throws a NullPointerException), and adding a duplicate element (throws an IllegalArgumentException).
	 * Tests check the list for size, and that elements are in the correct location using get() method.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// Test adding to the front, back and middle of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add("dog");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("dog", list.get(2));
		
		list.add("car");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("car", list.get(2));
		assertEquals("dog", list.get(3));
		
		// Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			// null element
		}
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("car", list.get(2));
		assertEquals("dog", list.get(3));
		
		
		// Test adding a duplicate element
		try {
			list.add("apple");
			fail();
		} catch (IllegalArgumentException e) {
			// duplicate element
		}
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("car", list.get(2));
		assertEquals("dog", list.get(3));		
	}
	
	/**
	 * Tests get() method for getting an element from an empty list, at an index less than 0, and at index(size), which all throw an IndexOutOfBoundsException.
 	 * Tests check the list for size, and that elements are in the correct location using get() method.
 	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		// Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// empty list
		}
		
		// Add some elements to the list
		list.add("a");
		list.add("b");
		list.add("c");
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		
		// Test getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// element at index < 0
		}
				
		// Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			// element at index(size)
		}
		
	}
	
	/**
	 * Tests remove() method for removing an element from the front, middle and back of a list, from an empty list, at an index less than 0 after four elements are added, and at index(size), which all throw an IndexOutOfBoundsException. 
	 * Tests check the list for size, and that elements are in the correct location using get() method.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// empty list
		}
		
		// Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		assertEquals(4, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		assertEquals("d", list.get(3));
		
		// Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// element at index < 0
		}
		
		// Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			// element at index(size)
		}
		
		// Test removing a middle element
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("d", list.get(2));
		
		// Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		
		// Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("b", list.get(0));		
		
		// Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
		
	}
	
	/**
	 * Tests indexOf() method to get the index of an element in an empty list, get the index of elements in various positions in the list after adding elements, get the index of an element in an empty list, get the index of elements not in the list, and getting the index of a null element.
 	 * Tests check the list for size, and that elements are in the correct location using get() method.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		int x = 0;
		
		// Test indexOf on an empty list
		x = list.indexOf("a");
		assertEquals(-1, x);
		
		// Add some elements
		list.add("a");
		list.add("b");
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		
		// Test various calls to indexOf for elements in the list
		//and not in the list
		x = list.indexOf("a");
		assertEquals(0, x);
		x = list.indexOf("b");
		assertEquals(1, x);
		x = list.indexOf("c");
		assertEquals(-1, x);
		
		// Test checking the index of null
		try {
			x = list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			// index of null
		}
	}
	
	/**
	 * Tests clear() method by adding an element to the list, clearing the list, and checking that the list is empty.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("a");
		list.add("b");
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
				
		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests isEmpty() method by checking a new list is empty, adding an element, and checking that list is no longer empty.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Check that the list starts empty
		assertTrue(list.isEmpty());
		
		// Add at least one element
		list.add("a");
		assertEquals(1, list.size());
		
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());

	}

	/**
	 * Tests the contains() method by checking if an empty list contains an element, adding elements and checking if the list contains an element in the list, and checking if a list contains an element not in the list.
 	 * Tests check the list for size, and that elements are in the correct location using get() method.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("a"));
		
		// Add some elements
		list.add("a");
		list.add("b");
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		
		// Test some true and false cases
		assertTrue(list.contains("a"));
		assertTrue(list.contains("b"));
		assertFalse(list.contains("c"));
	
	}
	
	/**
	 * Tests the equals() method by making two lists that are the same and one list that is different, and checking for equality and non-equality by checking if the specified object is this list, or that the specified object is also a list, and both lists have the same size, and both lists have the same elements in the same order.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		assertEquals(2, list1.size());
		assertEquals("a", list1.get(0));
		assertEquals("b", list1.get(1));

		list2.add("a");
		list2.add("b");
		assertEquals(2, list2.size());
		assertEquals("a", list2.get(0));
		assertEquals("b", list2.get(1));
		
		list3.add("a");
		assertEquals(1, list3.size());
		assertEquals("a", list3.get(0));
		
		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));

		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));

		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list2));
		
	}
	
	/**
	 * Tests the hashCode() method by making two lists that are the same and one list that is different, and checking that the hash codes are equal or not equal.
	 */ 
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		assertEquals(2, list1.size());
		assertEquals("a", list1.get(0));
		assertEquals("b", list1.get(1));

		list2.add("a");
		list2.add("b");
		assertEquals(2, list2.size());
		assertEquals("a", list2.get(0));
		assertEquals("b", list2.get(1));
		
		list3.add("a");
		assertEquals(1, list3.size());
		assertEquals("a", list3.get(0));
				
		// Test for the same and different hashCodes
		assertTrue(list1.hashCode() == list2.hashCode());
		assertFalse(list1.hashCode() == list3.hashCode());
		assertFalse(list2.hashCode() == list3.hashCode());
		
		
	}

}
 