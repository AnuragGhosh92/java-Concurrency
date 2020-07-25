package com.concurrency.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The Class Processor3 implements Callable.
 * 
 * The Callable interface is used when you need to return results of sub-threads.
 * 
 * @author anuragghosh
 */
class Processor3 implements Callable<String>{
	
	private int id;
	
	public Processor3(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		return "ID : " + id;
	}
	
}

public class CallableAndFuture {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		List<Future<String>> list = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			Future<String> future = executorService.submit(new Processor3(i));
			list.add(future);
		}
		
		System.out.println("main thread : submitted request to executorService");
		
		for(Future<String> future : list) {
			try {
				System.out.println(future.get()); // IMPORTANT : Blocking code - Waits till computation is complete and then returns result.
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("main thread : all future results captured.");
		
		executorService.shutdown();	// does not interrupt running threads, but will not entertain new tasks.
		
		System.out.println("main thread : executorService is shutdown");
	}
}
