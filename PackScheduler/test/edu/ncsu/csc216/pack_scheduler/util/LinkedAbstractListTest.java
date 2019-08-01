package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for LinkedAbstractList class
 * @author mlee25 Michael Lee
 */
public class LinkedAbstractListTest {

	/**
	 * Test method for LinkedAbstractList constructor
	 * @author mlee25 Michael Lee
	 */
	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> list = null;
		//negative capacity
		try {
			list = new LinkedAbstractList<String>(-1);			
		} catch (IllegalArgumentException e) {
			assertEquals("invalid construction", e.getMessage());
		}
		//zero capacity
		try {
			list = new LinkedAbstractList<String>(0);
		} catch (IllegalArgumentException e) {
			assertEquals("invalid construction", e.getMessage());
		}
		//valid lists
		try {
			list = new LinkedAbstractList<String>(1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(0, list.size());
		assertEquals(1, list.getCapacity());
		list = new LinkedAbstractList<String>(5);
		assertEquals(0, list.size());
		assertEquals(5, list.getCapacity());
	}

	/**
	 * Test method for add
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> list = null;
		list = new LinkedAbstractList<String>(5);
		assertEquals(0, list.size());
		assertEquals(5, list.getCapacity());
		
		// test negative index
		try {
			list.add(-1, "fail");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("invalid position", e.getMessage());
		}
		
		// test index > size
		try {
			list.add(1, "fail");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("invalid position", e.getMessage());
		}

		// test null data
		try {
			list.add(0, null);
			fail();
		} catch (NullPointerException e) {
			// invalid
		}
		
		// add valid to position 0 (front)
		list.add(0, "a");
		assertEquals(1, list.size());
		assertEquals("a", list.get(0));
		assertEquals("a", list.getBack());
		// add valid to position 0 (front)
		list.add(0, "b");
		assertEquals(2, list.size());
		assertEquals("b", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("a", list.getBack());
		// add valid to position 2 (back)
		list.add(2, "c");
		assertEquals(3, list.size());
		assertEquals("b", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("c", list.get(2));
		assertEquals("c", list.getBack());
		// add valid to position 1 
		list.add(1, "d");
		assertEquals(4, list.size());
		assertEquals("b", list.get(0));
		assertEquals("d", list.get(1));
		assertEquals("a", list.get(2));
		assertEquals("c", list.get(3));
		assertEquals("c", list.getBack());

		//Test add duplicate
		try {
			list.add(0, "a");		
		} catch (IllegalArgumentException e) {
			assertEquals("duplicate", e.getMessage());
			assertEquals(4, list.size());
			assertEquals("b", list.get(0));
			assertEquals("d", list.get(1));
			assertEquals("a", list.get(2));
			assertEquals("c", list.get(3));
			assertEquals("c", list.getBack());
		}

		// add to capacity
		list.add(1, "e");
		// add beyond capacity
		try {
			list.add(1, "f");
		} catch (IllegalArgumentException e) {
			assertEquals("exceeds capacity", e.getMessage());
		}

		
		//Test add beyond capacity
		try {
			list.add(0, "x");
		} catch (IllegalArgumentException e) {
			assertEquals("exceeds capacity", e.getMessage());
			assertEquals(5, list.size());
		}
	}

		/**
		 * Test method for remove.
		 */
		@Test
		public void testRemove() {
			LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
			//Add elements
			list.add(0, "d");
			assertEquals("d", list.getBack());
			list.add(0, "c");
			assertEquals("d", list.getBack());
			list.add(0, "b");
			assertEquals("d", list.getBack());
			list.add(0, "a");
			assertEquals("d", list.getBack());
			assertEquals(4, list.size());
			assertEquals("a", list.get(0));
			assertEquals("b", list.get(1));
			assertEquals("c", list.get(2));
			assertEquals("d", list.get(3));
			assertEquals("d", list.getBack());

			Object s1 = null;
			//Test remove invalid indexes
			try {
				s1 = list.remove(-1);
				fail();
			} catch (IndexOutOfBoundsException e) {
				//invalid
			}
			assertEquals(4, list.size());
			try {
				s1 = list.remove(4);
				fail();
			} catch (IndexOutOfBoundsException e) {
				//invalid
			}

			//Confirm back element
			assertEquals("d", list.getBack());

			
			//Test valid remove at end
			s1 = list.remove(3);
			assertTrue(s1.equals("d"));
			assertEquals(3, list.size());
			assertEquals("a", list.get(0));
			assertEquals("b", list.get(1));
			assertEquals("c", list.get(2));
			assertEquals("c", list.getBack());

			//Test valid remove in middle
			Object s2 = list.remove(1);
			assertTrue(s2.equals("b"));
			assertEquals(2, list.size());
			assertEquals("a", list.get(0));
			assertEquals("c", list.get(1));			
			assertEquals("c", list.getBack());			

			//Test valid remove at front
			Object s3 = list.remove(0);
			assertTrue(s3.equals("a"));
			assertEquals(1, list.size());
			assertEquals("c", list.get(0));
			assertEquals("c", list.getBack());
	}

		/**
		 * Test method for set.
		 */
		@Test
		public void testSet() {
			LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
			//Add elements
			list.add(0, "d");
			list.add(0, "c");
			list.add(0, "b");
			list.add(0, "a");
			assertEquals(4, list.size());
			assertEquals("a", list.get(0));
			assertEquals("b", list.get(1));
			assertEquals("c", list.get(2));
			assertEquals("d", list.get(3));

			String s = null;
			//Test set null
			try {
				s = list.set(1, null);
				fail();
			} catch (NullPointerException e) {
				assertEquals(4, list.size());
			}
			//Test set duplicate
			try {
				s = list.set(0, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertEquals(4, list.size());
				assertEquals("a", list.get(0));
			}
			//Test set out of bounds 
			try {
				s = list.set(4, "y");
				fail();
			} catch (IndexOutOfBoundsException e) {
				assertEquals(4, list.size());
			}

			//Test valid set at front
			s = list.set(0, "x");
			assertTrue(s.equals("a"));
			assertEquals(4, list.size());
			assertEquals("x", list.get(0));
			assertEquals("b", list.get(1));			
			assertEquals("c", list.get(2));			
			assertEquals("d", list.get(3));			

			//Test valid set at middle
			s = list.set(2, "y");
			assertTrue(s.equals("c"));
			assertEquals(4, list.size());
			assertEquals("x", list.get(0));
			assertEquals("b", list.get(1));			
			assertEquals("y", list.get(2));			
			assertEquals("d", list.get(3));			

			//Test valid set at back
			s = list.set(3, "z");
			assertTrue(s.equals("d"));
			assertEquals(4, list.size());
			assertEquals("x", list.get(0));
			assertEquals("b", list.get(1));			
			assertEquals("y", list.get(2));			
			assertEquals("z", list.get(3));			

		}

		/**
		 * Test method for get.
		 */
		@Test
		public void testGet() {
			Object e = null;
			//Add elements
			LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
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
			LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
			assertEquals(0, list.size());
			list.add(0, "a");
			assertEquals(1, list.size());
			list.add(1, "b");
			assertEquals(2, list.size());
	}

		/**
		 * Test method for setCapactiy.
		 */
		@Test
		public void testSetCapacity() {
			LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
			assertEquals(0, list.size());
			assertEquals(5, list.getCapacity());
			list.setCapacity(10);
			assertEquals(0, list.size());
			assertEquals(10, list.getCapacity());
	}
	
}
