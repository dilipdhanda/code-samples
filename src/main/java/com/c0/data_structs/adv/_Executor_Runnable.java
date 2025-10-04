package com.c0.data_structs.adv;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _Executor_Runnable {

    public static class WorkerThread implements Runnable {
        String command;
        public WorkerThread(String s){
            this.command=s;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) { e.printStackTrace(); }
            // process command
            System.out.println(Thread.currentThread().getName()+" End.");
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5); // creates 5 thread, so not need tocreate threads anymore
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker); // 10 worker.run()s added but thread to run them are still 5
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}

