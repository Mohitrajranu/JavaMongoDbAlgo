package com.mohit.thread;

import java.util.concurrent.CountDownLatch;

class CancellableCountDownLatch extends CountDownLatch {

	protected volatile boolean isCancelled = false;

	public CancellableCountDownLatch(int count) {
		super(count);
	}

	public void cancel() {
		if (getCount() == 0) {
			return;
		}
		this.isCancelled = true;
		while (getCount() > 0) {
			countDown();
		}
	}

}

// Login Job
class LoginWaitNew implements Runnable {

	final CancellableCountDownLatch cancellableCountDownLatch;

	public LoginWaitNew(CancellableCountDownLatch cancellableCountDownLatch) {
		this.cancellableCountDownLatch = cancellableCountDownLatch;
	}

	@Override
	public void run() {
		try {
			System.out.println("Thread " + Thread.currentThread().getName() + " will wait for all system check to pass");
			cancellableCountDownLatch.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		System.out.println("");

		if (!cancellableCountDownLatch.isCancelled) {
			System.out.println("Login pre-check passed. Now you can Login.");
		} else {
			System.out.println("Error Occured. Try again.");
		}
	}

}

// Class to check Login pre-cheks
class StartupTaskCheckNew implements Runnable {

	final CancellableCountDownLatch cancellableCountDownLatch;

	public StartupTaskCheckNew(CancellableCountDownLatch cancellableCountDownLatch) {
		this.cancellableCountDownLatch = cancellableCountDownLatch;
	}

	@Override
	public void run() {
		try {
			System.out.println("Disk Ready.");
			Thread.sleep(1000);
			cancellableCountDownLatch.countDown();

			System.out.println("Network Ready.");
			Thread.sleep(1000);
			cancellableCountDownLatch.countDown();

			throw new InterruptedException();

			/*
			 * System.out.println("Keyboard Ready."); Thread.sleep(1000);
			 * cancellableCountDownLatch.countDown();
			 * 
			 * System.out.println("Mouse Ready."); Thread.sleep(1000);
			 * cancellableCountDownLatch.countDown();
			 */

		} catch (InterruptedException e) {
			cancellableCountDownLatch.isCancelled = true;
			cancellableCountDownLatch.cancel();
			Thread.currentThread().interrupt();
		}
	}

}
public class CancellableCountDownLatchExample {

	public static void main(String[] args) {

		CancellableCountDownLatch cancellableCountDownLatch = new CancellableCountDownLatch(4);

		StartupTaskCheckNew check = new StartupTaskCheckNew(cancellableCountDownLatch);
		LoginWaitNew wait = new LoginWaitNew(cancellableCountDownLatch);

		new Thread(check, "Sys Check Thread").start();
		new Thread(wait, "Login Thread").start();
	}
}
