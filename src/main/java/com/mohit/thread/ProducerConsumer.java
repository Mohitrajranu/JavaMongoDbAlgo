package com.mohit.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProducerConsumer {

	public static void main(String[] args) throws InterruptedException{
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(50);
	
		class Consumer implements Callable<String>{
			public String call() throws InterruptedException{
				int count = 0;
				while(count++ <50){
					queue.take();
				}
				return "Consumed " + (count -1 );
			}
		}
		
		class Producer implements Callable<String>{
			public String call() throws InterruptedException{
				int count = 0;
				while(count++ <50){
					queue.put(Integer.toString(count));
				}
				return "Produced " + (count -1 );
			}
		}
		
		List<Producer> producers = new ArrayList<>();
		for(int i =0;i<4;i++){
			producers.add(new Producer());
		}
		List<Consumer> consumers = new ArrayList<>();
		for(int i =0;i<4;i++){
			consumers.add(new Consumer());
		}
	
		System.out.println("Producer and Consumer Launched");
		
		List<Callable<String>> producerandconsumers = new ArrayList<>();
		producerandconsumers.addAll(producers);
		producerandconsumers.addAll(consumers);
		
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		try{
			
				List<Future<String>> futures = executorService.invokeAll(producerandconsumers);
				futures.forEach(future ->{
					try{
						System.out.println(future.get());
					}catch(InterruptedException | ExecutionException e){
						System.out.println(e.getMessage());
					}
					
				});
		}finally{
			//System.out.println(buffer.size());
			executorService.shutdown();
		}
		

	}
}
