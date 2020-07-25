package com.concurrency.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anuragghosh
 *
 */
class Processor2 {
	

	private List<Integer> list = new ArrayList<>();
	
	private static final int LIMIT = 5;
	private static final int BOTTOM_LIMIT = 0;
	
	private final Object lock = new Object();
	
	/**
	 * Repeatedly adds an element to the list till LIMIT size.
	 * @throws InterruptedException 
	 */
	public void producer() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == LIMIT) {
					// list is full, release lock and wait for consumer thread to remove items.
					System.out.println("List size has Maxed out, Waiting for consumption..");
					lock.wait();
				}else {
					System.out.println("Adding to list value : " + list.size());
					// add value to list.
					list.add(list.size());
					// notify to consumer thread, but lock will only be handed over once all tasks are finished
					// which basically means, in this infinite loop, when conditions are met that wait() is called.
					
					/* NOTE : But it's very important to call the notify here, because if we don't the consumer's already waiting method 
					 * will never be woken up to resume work, and we would ultimately have 2 threads waiting to acquire the same lock. */
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
	
	/**
	 * Repeatedly removes an element from the list till BOTTOM limit size
	 * @throws InterruptedException 
	 */
	public void consumer() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == BOTTOM_LIMIT) {
					// list is empty, release lock and wait for producer thread to fill it up again.
					lock.wait();
				} else {
					// removed items from list
					System.out.println("Removing item from list " + list.remove(list.size()-1));
					/*
					 * after removing, notify waiting producer method that removal task is complete, and lock can be handed over.
					 * But lock will actually be handed over once current execution is complete, i.e. in this case
					 * execution will never be over in this infinite loop, until the wait() method is called.
					 * 
					 * NOTE : But it's very important to call the notify here, because if we don't the producer's already waiting method 
					 * will never be woken up to resume work, and we would ultimately have 2 threads waiting to acquire the same lock.
					 * */					 
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
}

public class ProducerConsumer {

	
	public static void main(String[] args) {
		
		Processor2 processor = new Processor2();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		t1.start();
		t2.start();
	}
}
