package com.concurrency.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample {
	
	public static void main(String[] args) {
		
		/** Executors.newCachedThreadPool() -> this executorService can dynamically reuse (waiting) threads. 
		 * if no waiting threads, it will create a new thread to do the job.
		 * efficient solution.
		 * 
		 * Executors.newFixedThreadPool(3) -> this service creates a fixed number of threads.
		 * when all threads are running, no new thread is created till a thread gets free.
		 * 
		 * Executors.newSingleThreadFactory() -> service uses a since thread for the job
		 * */
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0; i<5; i++)	// creates 5 jobs/tasks to be done.
			executorService.submit(new Worker3());
	}

}

class Worker3 implements Runnable {

	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}