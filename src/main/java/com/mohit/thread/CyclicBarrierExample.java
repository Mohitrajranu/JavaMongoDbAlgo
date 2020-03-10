package com.mohit.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


	// Mcdonalds Job
	class McdonaldsMealTask implements Runnable {

		private final CyclicBarrier barrier;

		public McdonaldsMealTask(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			
			String threadName = Thread.currentThread().getName();
			
			System.out.println("Thread starting :-" + threadName);
			
			switch (threadName) {
			case "BurgerMachine":
				System.out.println("Preparing Burger");
				break;
				
			case "FriesMachine":
				System.out.println("Preparing Fries");
				break;
				
			case "ColaMachine":
				System.out.println("Preparing Cola");
				break;
			}
			
			try {
				this.barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			
			System.out.println("Thread finished :-" + threadName);
		}
	}
  public class CyclicBarrierExample {
  public static void main(String[] args) {
		
		Runnable barrierAction = new Runnable() {

			@Override
			public void run() {
				System.out.println("Order Complete. Put in Tray and deliver");
			}
		};

		CyclicBarrier barrier = new CyclicBarrier(3, barrierAction);
		
		McdonaldsMealTask task = new McdonaldsMealTask(barrier);
		
		new Thread(task,"BurgerMachine").start();
		new Thread(task,"FriesMachine").start();
		new Thread(task,"ColaMachine").start();
	}
}
