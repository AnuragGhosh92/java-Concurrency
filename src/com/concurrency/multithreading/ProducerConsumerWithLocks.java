package com.concurrency.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();		
	
	public void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Producer running...");
		// works exactly like wait() which was also called on the locked object.
		// releases the lock, waiting to acquire it again.
		condition.await();
		System.out.println("Producer running again...");
		lock.unlock();
	}
	
	public void consumer() throws InterruptedException {
		lock.lock();
		Thread.sleep(3000);
		System.out.println("Consumer runnning");
		// exactly works like lock.notify().
		// wakes up one thread waiting to acquire lock. Does not release lock until lock.unlock() is called.
		condition.signal();
		lock.unlock();
	}

	public static void main(String[] args) {
		
		ProducerConsumerWithLocks app = new ProducerConsumerWithLocks();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					app.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					app.consumer();
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
