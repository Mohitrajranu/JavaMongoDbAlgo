package com.mohit.thread;
import java.util.concurrent.CountDownLatch;
//Login Job
class LoginWait implements Runnable {

	final CountDownLatch countDownLatch;
	
	public LoginWait(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Thread " + Thread.currentThread().getName() + " will wait for all system check to pass");
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("");
		System.out.println("Login pre-check passed. Now you can Login.");
	}
	
}

//Class to check Login pre-cheks
class StartupTaskCheck implements Runnable {

	final CountDownLatch countDownLatch;
	
	public StartupTaskCheck(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Disk Ready.");
			Thread.sleep(1000);
			countDownLatch.countDown();
			
			System.out.println("Network Ready.");
			Thread.sleep(1000);
			countDownLatch.countDown();
			
			System.out.println("Keyboard Ready.");
			Thread.sleep(1000);
			countDownLatch.countDown();
			
			System.out.println("Mouse Ready.");
			Thread.sleep(1000);
			countDownLatch.countDown();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
public class CountDownLatchExample {
public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(4);
		
		StartupTaskCheck check = new StartupTaskCheck(countDownLatch);
		LoginWait wait = new LoginWait(countDownLatch);
		
		new Thread(check,"Sys Check Thread").start();
		new Thread(wait,"Login Thread").start();
	}
	
}
