package com.concurrency.multithreading;

public class SynchronizedKeyword {
	
	private static int counter = 0;
	
	/**
	 * synchronized keyword here creates a class-intrinsic lock, meaning that if a thread has acquired a lock
	 * other threads cannot call <i>any</i> of the methods of that class.
	 * i.e. equal to synchronized(SynchronizedKeyword.class)
	 * */
	private static synchronized void increment() {
		counter++;
	}
	
	public static void process() {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<100; i++)
					increment();
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0; i<100; i++)
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
	}
	
	public static void main(String[] args) {
		process();
		System.out.println(counter);
	}

}
