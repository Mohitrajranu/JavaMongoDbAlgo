package com.mohit.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

	//not thread safe
	/*private static int counter = 0;*/
	
	//thread safe 
	private static AtomicInteger counter = new AtomicInteger(0);
	public static void main(String[] args) {
		
		class Incrementer implements Runnable{

			public void run() {

				for(int i=0;i<1_000;i++){
					//counter++;
					counter.incrementAndGet();
				}
				
			}
			
		}
		
		class Decrementer implements Runnable{

			public void run() {

				for(int i=0;i<1_000;i++){
					//counter--;
					counter.decrementAndGet();
				}
				
			}
			
		}
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		List<Future<?>> futures = new ArrayList<>();
		try{
			for(int i=0;i<4;i++){
				futures.add(executorService.submit(new Incrementer()));
			}
			for(int i=0;i<4;i++){
				futures.add(executorService.submit(new Decrementer()));
			}
			
			futures.forEach(future ->{
				try{
					future.get();
				}catch(InterruptedException | ExecutionException e){
					System.out.println(e.getMessage());
				}
			});
			
			System.out.println(" counter = "+counter);
		}finally{
			
			executorService.shutdown();
		}
		
	}
	
}
