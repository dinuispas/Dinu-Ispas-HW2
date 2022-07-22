package com.company;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i ++){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection.getInstance().connect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("The first semaphore is now red");

        ExecutorService executor1 = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i ++){
            executor1.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection.getInstance().connect1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executor1.shutdown();

        executor1.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Second semaphore is red, all cars have passed");
    }
}
