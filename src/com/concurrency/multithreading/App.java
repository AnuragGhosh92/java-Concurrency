package com.concurrency.multithreading;

class Runner1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {			
			System.out.println("Runner 1: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Runner2 implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {			
			System.out.println("Runner 2: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Runner3 extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {			
			System.out.println("Runner 3: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class App {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runner1());
		Thread t2 = new Thread(new Runner2());
		
		t1.start(); // calls the run method of the Runnable impl class.
		t2.start();
		
		// 2nd way of creating and running something in a distinct Thread - extend the Thread class
		// (which implements the Runnable interface)
		Runner3 t3 = new Runner3();
		t3.start();	// Runner3 itself is a Thread, hence can be started.
		
		try {
			t1.join();	// waits the main thread here till thread t1 dies, i.e. finishes execution
			//t2.join(); // will not wait for thread 2 to complete
			t3.join();	// waits for t3 to complete execution.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished the tasks...");
		
//		Runner1 runner1 = new Runner1();
//		Runner2 runner2 = new Runner2();
//		
//		// executes sequentially
//		runner1.run();
//		runner2.run();
		
	}
}
