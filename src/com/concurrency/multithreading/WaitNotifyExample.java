package com.concurrency.multithreading;

class Processor {
	
	/*
	 * NOTE: All wait(), notify(), notifyAll() methods can only called by a thread that has acquired lock on an object.
	 * i.e. from inside a synchronized method or block.
	 * */
	
	public void produce() throws InterruptedException {
		synchronized(this) {
			System.out.println("Inside Producer method...");
			/*
			 * makes the current thread to wait, and the lock acquired by that current thread is released
			 * so other threads can acquire it. Waits for those "other" threads to notify that their work is done.
			 * 
			 * "I release the lock on this object, and I am waiting to acquire it back"
			 * */
			wait();
			// once notified to resume, it ... resumes!
			System.out.println("Producer method again...");
		}
	}
	
	public void consume() throws InterruptedException {
		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("Consumer method...");
			/* 
			 * Wakes up a single waiting thread. If there are n threads waiting, arbitrarily wakes up one. 
			 * 
			 * */
			//notify();
			/* 
			 * Wakes up ALL waiting threads. that's the only difference between notify and notifyAll. 
			 * 
			 * */
			notifyAll();
			
			/* 
			 * Woken up thread will not resume, until this thread releases the lock by finishing execution. 
			 * 
			 * */
			System.out.println("Inside consumer again.. notifyAll called");
			Thread.sleep(3000);
		}
	}
}

public class WaitNotifyExample {
	
	public static void main(String[] args) {
		
		Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
}
