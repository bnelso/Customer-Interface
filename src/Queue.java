///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  StudentCenter.java
//File:             Queue.java
//Semester:         CS 367 Spring 2016
//
//Author:           Nikhil Kumar
//CS Login:         nkumar
//Lecturer's Name:  Deb Deppeler
//Lab Section:      ---
/**
 * An ordered collection of items, where items are added to the rear and 
 * removed
 * from the front.
 */
public class Queue<E> implements QueueADT<E>
{
	private static final int INITIAL_SIZE = 10;	//starting queue size
	private int numItems;						//number of items in queue
	private E[] queue;							//circular array
	private int frontIndex;						//front position in queue
	private int rearIndex;						//rear position in queue
	
	//constructor initializes fields
	public Queue()
	{
		numItems = 0;
		queue = (E[]) new Object[INITIAL_SIZE];
		frontIndex = rearIndex = 0;
	}

	/**
	 * Adds an item to the rear of the queue.
	 * 
	 * @param item
	 *            the item to add to the queue.
	 * @throws IllegalArgumentException
	 *             if item is null.
	 */
	public void enqueue(E item)
	{
		//argument is null
		if(item == null)
			throw new IllegalArgumentException();
		
		//queue's array is full
		if(numItems == queue.length)
			expandCapacity();
		
		//manipulate circular array's front/rear positions
		if(numItems != 0)
			rearIndex = (rearIndex+1)%queue.length;
		
		//add item
		queue[rearIndex] = item;
		numItems++;
	}

	/**
	 * Removes an item from the front of the Queue and returns it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	public E dequeue()
	{
		//queue is empty
		if(numItems == 0)
			throw new EmptyQueueException();
		
		E item = queue[frontIndex];
		
		//manipulate circular array's front/rear positions
		if(numItems != 1)
			frontIndex = (frontIndex+1)%queue.length;
		
		//remove item
		numItems--;
		
		return item;
	}

	/**
	 * Returns the item at front of the Queue without removing it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	public E peek()
	{
		//empty queue
		if(numItems == 0)
			throw new EmptyQueueException();
		
		return queue[frontIndex];
	}

	/**
	 * Returns true iff the Queue is empty.
	 * 
	 * @return true if queue is empty; otherwise false.
	 */
	public boolean isEmpty()
	{
		return numItems == 0;
	}

	/**
	 * Removes all items in the queue leaving an empty queue.
	 */
	public void clear()
	{
		numItems = 0;
		frontIndex = rearIndex;
	}

	/**
	 * Returns the number of items in the Queue.
	 * 
	 * @return the size of the queue.
	 */
	public int size()
	{
		return numItems;
	}

	/*
	 * Increases the size of the base array used to implement the queue
	 * 
	 * Used situationally in the enqueue() method
	 */
	private void expandCapacity()
	{
		//create new array with twice original size and copy old items over
		E[] newQueue = (E[]) new Object[queue.length*2];
		System.arraycopy(queue, frontIndex, newQueue, 0, 
				queue.length-frontIndex);
		System.arraycopy(queue, 0, newQueue, queue.length-frontIndex, 
				frontIndex);
		
		//assign new array to field
		queue = newQueue;
		
		//modify front/rear positions
		frontIndex = 0;
		rearIndex = numItems-1;
	}
}
