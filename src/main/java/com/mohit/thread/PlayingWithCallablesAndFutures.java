package com.mohit.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PlayingWithCallablesAndFutures {

	public static void main(String[] args) throws ExecutionException,InterruptedException,TimeoutException{
		
		Callable<String> task = () ->{
			
			Thread.sleep(100);
			return "I am in thread "+Thread.currentThread().getName();
		};
		
		ExecutorService execSer = Executors.newFixedThreadPool(4);
		try{
			for(int i=0;i<10;i++){
			Future<String> future=	execSer.submit(task);
			System.out.println("I get "+ future.get(100, TimeUnit.MILLISECONDS));
			}
		}finally{
			execSer.shutdown();
		}
		
	}
}
