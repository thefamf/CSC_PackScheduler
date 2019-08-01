/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for ArrayList class
 * @author mlee25 Michael Lee
 */
public class ArrayListTest {

	/**
	 * Test method for ArrayList constructor.
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for add.
	 */
	@Test
	public void testAdd() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		list.add(3, "d");
		list.add(4, "e");
		list.add(5, "f");
		list.add(6, "g");
		list.add(7, "h");
		list.add(8, "i");
		assertEquals(9, list.size());

		//Test add null
		try {
			list.add(0, null);		
		} catch (NullPointerException e1) {
			assertEquals(9, list.size());
			assertEquals("a", list.get(0));
		}
		//Test add and shift
		list.add(1, "j");
		assertEquals(10, list.size());
		assertEquals("a", list.get(0));
		assertEquals("j", list.get(1));
		assertEquals("b", list.get(2));
		assertEquals("c", list.get(3));
		assertEquals("d", list.get(4));
		assertEquals("e", list.get(5));
		assertEquals("f", list.get(6));
		assertEquals("g", list.get(7));
		assertEquals("h", list.get(8));
		assertEquals("i", list.get(9));
		//test add requiring growArray and shift
		list.add(9, "k");
		assertEquals("k", list.get(9));
		assertEquals("i", list.get(10));
		assertEquals(11, list.size());
		//Test add duplicate
		try {
			list.add(10, "a");		
		} catch (IllegalArgumentException e1) {
			assertEquals(11, list.size());
			assertEquals("i", list.get(10));
		}
		//Test index beyond capacity
		try {
			list.add(20, "x");
			assertEquals(null, list.get(20));
		} catch (IndexOutOfBoundsException e) {
			assertEquals(11, list.size());
		}
	}

		/**
		 * Test method for remove.
		 */
		@Test
		public void testRemove() {
			//Add elements
			ArrayList<String> list = new ArrayList<String>();
			assertEquals(0, list.size());
			list.add(0, "a");
			list.add(1, "b");
			list.add(2, "c");
			assertEquals(3, list.size());
			//Test remove and shift
			Object e = list.remove(1);
			assertTrue(e.equals("b"));
			assertEquals(2, list.size());
			assertEquals("a", list.get(0));
			assertEquals("c", list.get(1));			
			//Test remove invalid
			try {
				e = list.remove(2);		
			} catch (IndexOutOfBoundsException e1) {
				assertEquals(2, list.size());
			}
	}

		/**
		 * Test method for set.
		 */
		@Test
		public void testSet() {
			//Add elements
			ArrayList<String> list = new ArrayList<String>();
			assertEquals(0, list.size());
			list.add(0, "a");
			list.add(1, "b");
			list.add(2, "c");
			assertEquals(3, list.size());

			//Test valid set 
			Object e = list.set(1, "x");
			assertTrue(e.equals("b"));
			assertEquals(3, list.size());
			assertEquals("a", list.get(0));
			assertEquals("x", list.get(1));			
			assertEquals("c", list.get(2));			
			//Test set null
			try {
				e = list.set(1, null);		
			} catch (NullPointerException e1) {
				assertEquals(3, list.size());
			}
			//Test set duplicate
			try {
				e = list.set(1, "a");		
			} catch (IllegalArgumentException e1) {
				assertEquals(3, list.size());
				assertEquals("x", list.get(1));
			}
			//Test set out of bounds 
			try {
				e = list.set(3, "y");		
			} catch (IndexOutOfBoundsException e1) {
				assertEquals(3, list.size());
			}
	}

		/**
		 * Test method for get.
		 */
		@Test
		public void testGet() {
			Object e = null;
			//Add elements
			ArrayList<String> list = new ArrayList<String>();
			assertEquals(0, list.size());
			list.add(0, "a");
			list.add(1, "b");
			list.add(2, "c");
			assertEquals(3, list.size());

			//Test index < 0 
			try {
				e = list.get(-1);
			} catch (IndexOutOfBoundsException e1) {
				assertEquals(null, e);
			}
			//Test index = size			
			try {
				e = list.get(list.size());
			} catch (IndexOutOfBoundsException e1) {
				assertEquals(null, e);
			}
			//Test valid index
			e = list.get(0);
			assertTrue(e.equals("a"));
	}

		/**
		 * Test method for size.
		 */
		@Test
		public void testSize() {
			//Add elements
			ArrayList<String> list = new ArrayList<String>();
			assertEquals(0, list.size());
			list.add(0, "a");
			assertEquals(1, list.size());
			list.add(1, "b");
			assertEquals(2, list.size());
	}

}
