package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Test class for LinkedStack
 * @author mlee25 Michael Lee
 *
 */
public class LinkedStackTest {

	/**
	 * Test method for ArrayList constructor.
	 */
	@Test
	public void testLinkedStack() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		assertEquals(0, stack.size());
	}

	/**
	 * Test push()
	 */
	@Test
	public void testPush() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		stack.push("a");
		assertEquals("a", stack.pop());
		stack.push("b");
		stack.push("c");
		stack.push("d");
		assertEquals("d", stack.pop());
		assertEquals("c", stack.pop());
	}

	/**
	 * Test pop()
	 */
	@Test
	public void testPop() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		stack.push("a");
		assertEquals(1, stack.size());
		assertEquals("a", stack.pop());
		assertEquals(0, stack.size());
		stack.push("b");
		stack.push("c");
		stack.push("d");
		assertEquals(3, stack.size());
		assertEquals("d", stack.pop());
		assertEquals("c", stack.pop());
		assertEquals(1, stack.size());
		stack.push("e");
		stack.push("f");
		assertEquals("f", stack.pop());
		assertEquals(2, stack.size());

		stack = new LinkedStack<String>(10);
		try {
			stack.pop();
		} catch (EmptyStackException e) {
			assertEquals(0, stack.size());
		}
	}

	/**
	 * Test isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test size()
	 */
	@Test
	public void testSize() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		assertEquals(3, stack.size());
	}

	/**
	 * Test setCapacity()
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		assertEquals(3, stack.size());
		try {
			stack.setCapacity(2);
		} catch (IllegalArgumentException e) {
			assertEquals("invalid capacity", e.getMessage());
			assertEquals(3, stack.size());
		}
		stack.setCapacity(3);
		assertEquals(3, stack.size());
		try {
			stack.setCapacity(5);
			stack.push("d");
			stack.push("e");
			assertEquals(5, stack.size());
			stack.push("f");
		} catch (IllegalArgumentException e) {
			assertEquals("beyond capacity", e.getMessage());
			assertEquals(5, stack.size());
		}
	}
}
