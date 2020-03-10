package com.mohit.thread;

import java.util.concurrent.Semaphore;

class BridgeCrossingTask implements Runnable {

	private final Semaphore signal;
	
	public BridgeCrossingTask(Semaphore signal) {
		this.signal = signal;
	}
	
	@Override
	public void run() {
		
		System.out.println("Train " + Thread.currentThread().getName() + " reached at rail bridge and waiting for signal.");
		
		try {
			signal.acquire();
			
			System.out.println("Train " + Thread.currentThread().getName() + " got signal, is passing and will take 10 sec");
			
			Thread.sleep(10000);
			
		} catch(InterruptedException e) {
			
			Thread.interrupted();
			
		} finally {
			
			System.out.println("Train " + Thread.currentThread().getName() + " is passed.");
			signal.release();
			
		}
	}
	
}


public class SemaphoreExample {

	public static void main(String[] args) {
		
		Semaphore passToken = new Semaphore(1);
		
		BridgeCrossingTask task = new BridgeCrossingTask(passToken);
		
		new Thread(task,"Rajdhani").start();
		new Thread(task,"Shatabdi").start();
	}
	
}