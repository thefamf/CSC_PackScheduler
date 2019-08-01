package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Test class for ArrayQueue
 * @author mlee25 Michael Lee
 *
 */
public class ArrayQueueTest {

	/**
	 * Test method for ArrayList constructor.
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		assertEquals(0, queue.size());
	}

	/**
	 * Test enqueue
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		queue.enqueue("a");
		assertEquals("a", queue.dequeue());
		queue.enqueue("b");
		queue.enqueue("c");
		queue.enqueue("d");
		assertEquals("b", queue.dequeue());
		assertEquals("c", queue.dequeue());
	}

	/**
	 * Test dequeue
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		queue.enqueue("a");
		assertEquals(1, queue.size());
		assertEquals("a", queue.dequeue());
		assertEquals(0, queue.size());
		queue.enqueue("b");
		queue.enqueue("c");
		queue.enqueue("d");
		assertEquals(3, queue.size());
		assertEquals("b", queue.dequeue());
		assertEquals("c", queue.dequeue());
		assertEquals(1, queue.size());
		queue.enqueue("e");
		queue.enqueue("f");
		assertEquals("d", queue.dequeue());
		assertEquals(2, queue.size());

		queue = new ArrayQueue<String>(10);
		try {
			queue.dequeue();
		} catch (NoSuchElementException e) {
			assertEquals(0, queue.size());
		}
	}

	/**
	 * Test isEmpty
	 */
	@Test
	public void testIsEmpty() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

	/**
	 * Test size
	 */
	@Test
	public void testSize() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		assertEquals(3, queue.size());
	}

	/**
	 * Test setCapacity
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		assertEquals(3, queue.size());
		try {
			queue.setCapacity(2);
		} catch (IllegalArgumentException e) {
			assertEquals("invalid capacity", e.getMessage());
			assertEquals(3, queue.size());
		}
		queue.setCapacity(3);
		assertEquals(3, queue.size());
		try {
			queue.setCapacity(5);
			queue.enqueue("d");
			queue.enqueue("e");
			assertEquals(5, queue.size());
			queue.enqueue("f");
		} catch (IllegalArgumentException e) {
			assertEquals("beyond capacity", e.getMessage());
			assertEquals(5, queue.size());
		}
	}
}
