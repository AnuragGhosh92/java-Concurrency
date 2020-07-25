package com.concurrency.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

	private static int counter=0;
	
	/**
	 * Advantages of using lock over synchronized block - 
	 * Locking and unlocking can happen at different places in different methods, something that can't happen with synchronized keyword.
	 * new RentrantLock(boolean fairnessParameter) - when true gives lock to longest waiting thread, else random.
	 * 
	 * Disadvantage - 
	 * Need to surround code with try finally, else if exception occurs, thread can acquire lock forever.
	 * */
	private static Lock lock = new ReentrantLock();
	
	private static void increment() {
		lock.lock();
		
		try {
			for(int i=0; i<10000; i++)
				counter++;
		}finally {			
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				increment();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				increment();
			}
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Counter : " + counter);
	}
}
