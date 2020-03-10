package com.mohit.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

	public static void main(String[] args) throws InterruptedException {
		
		List<Integer> buffer = new ArrayList<>();
		Lock lock = new ReentrantLock();
		Condition isEmpty = lock.newCondition();
		Condition isFull = lock.newCondition();
		class Consumer implements Callable<String> {

			public String call() throws InterruptedException, TimeoutException{
			
				int count =0;
				while(count++ < 20){
					try{
						lock.lock();
						while(buffer.isEmpty()){
							
							if(!isEmpty.await(10,TimeUnit.MILLISECONDS)){
								throw new TimeoutException("Consumer Timed Out");
							}
						
						}
						buffer.remove(buffer.size() - 1);
						isFull.signalAll();
					}finally{
						lock.unlock();
					}
				}
				
				return "Consumed "+ (count - 1);
			}
		}
			class Producer implements Callable<String> {

				public String call() throws InterruptedException {
				
					int count =0;
					while(count++ < 20){
						try{
							lock.lock();
							while((buffer.size() == 15)){
								isFull.await();
							}
							buffer.add(1);
							isEmpty.signalAll();
						}finally{
							lock.unlock();
						}
					}
					
					return "Produced "+ (count - 1);
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
