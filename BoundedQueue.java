/**
* This implementation uses a single-linked list with sentinal nodes
* that point to the most recently added and least recently added elements
* in the queue. This makes enqueing and dequeueing more efficient.
*/
public class BoundedQueue<Integer>{

	/**
	* Exception thrown when a user tries creating a queue of size <= 0
	*/
	public static class SizeException extends Exception {
		public SizeException() {
			super();
		}
	}

	/**
	* Exception thrown when a user tries dequeue-ing from an empty queue
	*/
	public  static class EmptyQueueException extends Exception {
		public EmptyQueueException() {
			super();
		}
	}

	/**
	* Exception thrown when a user tries enqueue-ing from a full queue
	*/
	public static class FullQueueException extends Exception {
		public FullQueueException() {
			super();
		}
	}

	//the global properties contained in the bounded queue
	private static int maxSize; 
	private static int currentSize;
	private static Node back; //most recently added element
	private static Node front; //least recently added element

	/**
	* Each element in the queue is a node that contains an integer value,
	* a next node, and a previous node since this is basically a doubly-linked list
	* with a limit on the size of the list.
	*/
	private static class Node<Integer> {
		private int value;
		private Node next;
		private Node previous;

		public Node() {
			value = 0;
			next = null;
			previous = null;
		}
	}

	/**
	* BoundedQueue contructor
	*
	* throws SizeException when the max size of the queue is less than or equal to 0
	*/
	public BoundedQueue(int max) throws SizeException {
		if (max <= 0) {
			throw new SizeException();
		}
		maxSize = max;
		currentSize = 0;
		front = new Node();
		back = new Node();
		front.next = back;
		back.previous = front;
	}

	/**
	* Calculates current size of the queue. This is used primarily for testing
	* as seen in the junit testing.
	*
	* returns the current size of the queue
	*/
	public static int getSize() {
		return currentSize;
	}

	/**
	* Method to add elements to the queue
	*
	* int newValue the new integer value being added to the queue
	* throws FullQueueException when the queue is full and no element can be added
	*/
	public static void enqueue(int newValue) throws FullQueueException {
		if (currentSize == maxSize) {
			throw new FullQueueException();
		} else if (currentSize == 0) {
			Node temp = new Node();
			temp.value = newValue;
			front.next = temp;
			temp.previous = front;
			temp.next = back;
			back.previous = temp;
		} else { 
			Node temp = new Node();
			temp.value = newValue;
			temp.next = front.next;
			front.next.previous = temp;
			front.next = temp;
			temp.previous = front;
		}
		currentSize++;

	} 

	/**
	* Method to remove elements to the queue
	*
	* throws EmptyQueueException when the queue is empty and no element can be removed
	* returns the least recently added element
	*/
	public static int dequeue() throws EmptyQueueException {
		if (currentSize == 0) {
			throw new EmptyQueueException();
		} else {
			Node temp = back.previous;
			int returnVal = temp.value;
			temp.previous.next = back;
			back.previous = temp.previous;
			currentSize--;
			return returnVal;
			
		}
	}
}
