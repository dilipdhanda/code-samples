package com.c0.data_structs.adv;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _Executor_Callable_w_Future implements Callable<Integer> {
    int number;

    public _Executor_Callable_w_Future(int number) {
        this.number = number;
    }

    public Integer call() throws InvalidParamaterException {
        int fact=1;
        System.out.printf("task execute with param "+number+"\n");
        if(number < 0)
            throw new InvalidParamaterException("Number must be positive");
        for(int count=number;count>1;count--){
            fact=fact * count;
        }
        return fact;
    }

    private class InvalidParamaterException extends Exception {
        public InvalidParamaterException(String message) {
            super(message);
        }
    }

    public static void main(String[] args){
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            _Executor_Callable_w_Future task = new _Executor_Callable_w_Future(5);
//            task.call(); // can be called directly
            System.out.printf("Submitting task to Executor Service\n");
            Future<Integer> future= executorService.submit(task);
//            while (!executorService.isTerminated()){ // this did not hep execute future tasks
//                Thread.sleep(100);
//            }
//            executorService.invokeAll(Arrays.asList(task)); // this will run the task twice
//            Thread.sleep(100);
            System.out.printf("Shutting down Executor Service\n");
            executorService.shutdown(); // run the task and needed otherwise process keeps running even after main ends

            // Assertions are disabled by default in java. You need to manually enable them by adding -ea
            // to your command-line arguments when you invoke the java compiler.
            System.out.printf("Getting result from Future\n");
            assert 120 == future.get().intValue();  // will throw exception if result was exception e.g. in case of -3
            System.out.println("Success - result: "+future.get());
//            assert 121 == future.get().intValue();
        } catch (Exception ex)
        {
            System.out.println("Caught Exception: "+ex.toString());
        }
    }
}