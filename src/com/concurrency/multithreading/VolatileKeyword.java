package com.concurrency.multithreading;

class Worker implements Runnable{

	// volatile keyword ensures isTerminated is always read from main memory.
	private volatile boolean isTerminated = false;
	
	@Override
	public void run() {
		// isTerminated (if not specified as volatile) could potentially be read from the CPU core's cache.
		while(!isTerminated) {
			
			System.out.println("Hello from Worker class..");
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	
}

public class VolatileKeyword {
	
	public static void main(String[] args) {
		Worker worker = new Worker();
		Thread t1 = new Thread(worker);
		t1.start();
		
		// sleeping the main thread for 3 seconds.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// main thread sets variable to true.
		worker.setTerminated(true);
		System.out.println("Finished...");
	}
	
}
