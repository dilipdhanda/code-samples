package a_2;

import java.util.Vector;

public class Producer_Consumer {
}
//		Producer – Put/Add
//		-	if queue is full (reached maxsize) then wait (for cosumer to comsume),
//		-	else add and notify (for consumer to wake up and consume)
//		Consumer – Get
//		-	if queue empty then wait (for producer to add),
//		-	else get and remove first element and notify (for producer to add)
//By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to sleep.

class Producer extends Thread {
	static final int MAXQUEUE = 5;
	private Vector messages = new Vector();
	@Override
	public void run() {
		try {
			while (true) {
				putMessage();
				sleep(5000);
			}
		} catch (InterruptedException e) {
		}
	}

// PUT : if maxqueue reached then waits,
//	  else adds row and does notify (to wake up consumer to read row)
	private synchronized void putMessage() throws InterruptedException {
		while (messages.size() == MAXQUEUE) {
			wait();
		}
		messages.addElement(new java.util.Date().toString());
		System.out.println("put message");
		notify();
		//Later, when the necessary event happens, the thread that is running it calls notify() from a block synchronized on the same object.
	}

// GET : 1) first does notify (to give producer a chance to add to queue),
//	  2) if queue empty then waits,
//	     else reads a row and does notify (to wake up consumer to read row)
	// Called by Consumer
	public synchronized String getMessage() throws InterruptedException {
		notify(); // give chance to add to queue
		while (messages.size() == 0) {
			wait();//By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to sleep.
		}
		String message = (String) messages.firstElement();
		messages.removeElement(message);
		return message;
	}
}

class Consumer extends Thread {

	Producer producer;

	Consumer(Producer p) {
		producer = p;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = producer.getMessage();
				System.out.println("Got message: " + message);
				sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Producer producer = new Producer();
		producer.start();
		new Consumer(producer).start();
	}
}
