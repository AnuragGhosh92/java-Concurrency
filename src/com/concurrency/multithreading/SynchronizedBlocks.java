package com.concurrency.multithreading;

public class SynchronizedBlocks {

	private static int counter1 = 0;
	private static int counter2 = 0;
	
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();
	
	/** 
	 * faster implementation - every thread will acquire lock on the object lock1/lock2 only, 
	 * thus making other method available to other threads.
	 * */
	private static void increment1() {
		synchronized (lock1) {
			counter1++;
		}
	}
	
	private static void increment2() {
		synchronized (lock2) {			
			counter2++;
		}
	}
	
	public static void compute() {
		for (int i = 0; i < 100; i++) {
			increment1();
			increment2();
		}
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compute();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compute();
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
		
		System.out.println("Counter1:"+counter1);
		System.out.println("Counter2:"+counter2);
	}
}
