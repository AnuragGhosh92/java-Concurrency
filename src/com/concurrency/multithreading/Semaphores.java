package com.concurrency.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
	
	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(4, true);
	
	public void downloadData() {
		try {
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {			
			semaphore.release();
		}
	}
	
	private void download() throws InterruptedException {
		System.out.println("Downloading from the web...");
		Thread.sleep(2000);
	}
}

/**
 * The Class Semaphores.
 * 
 * @author anuragghosh
 */
public class Semaphores {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		//Thread t1 = new Thread(new Runnable() {
		
		// creates 12 tasks (and possibly 12 threads to run those 12 tasks) and runs them.
		for(int i=0; i<12; i++) {			
			executorService.execute(new Runnable() {
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
	}
}
