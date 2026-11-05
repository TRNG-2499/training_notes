package com.revature.strings;

public class StringThreadSafety {
	// static resources to access from threads
	static StringBuilder sbd = new StringBuilder("init"); // StringBuilder is mutable but NOT thread-safe
	static StringBuffer sbf = new StringBuffer("init");   // StringBuffer is mutable AND thread-safe (synchronized methods)

	public static void main(String[] args) {
		// initiate threads
		Thread t1 = new Thread(new Run(1)); // create thread 1, backed by a Runnable
		Thread t2 = new Thread(new Run(2)); // create thread 2, backed by a Runnable
		t1.start(); // starts t1 running concurrently (does NOT block main)
		t2.start(); // starts t2 running concurrently (does NOT block main)
		System.out.println("Threads started"); // likely prints before threads finish, since start() is non-blocking
		try {
			t1.join(); // pause main thread until t1 finishes
			t2.join(); // pause main thread until t2 finishes
			System.out.println("Threads finished execution"); // only reached after both threads complete
		} catch (InterruptedException e1) {
			e1.printStackTrace(); // handles case where main thread is interrupted while waiting
		}
		System.out.println("StringBuilder:"+sbd); // result may be inconsistent/corrupted due to unsynchronized concurrent access
		System.out.println("StringBuffer: "+sbf); // result will be consistent since StringBuffer synchronizes access
	}

	static class Run implements Runnable {
		int id; // unique identifier for each thread

		@Override
		public void run() {
			// each thread appends 100 times to the shared static fields
			for (int i=0;i<100;i++) {
				sbd.append(" T"+id); // unsynchronized — race condition possible when both threads write at once
				sbf.append(" T"+id); // synchronized internally — safe for concurrent access
			}
		}

		public Run(int id) {
			this.id = id; // stores which thread this Runnable represents
		}
	}
}
