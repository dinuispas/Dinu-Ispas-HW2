package com.company;

import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance = new Connection();

    private int cars = 0;

    private Semaphore sem = new Semaphore(10);
    private Semaphore sem1 = new Semaphore(10);

    private Connection(){

    }

    public static Connection getInstance(){
        return instance;
    }

    public void connect() throws InterruptedException {
        sem.acquire();

        try{
            carsPassingNS();
        }finally {
            sem.release();
        }
    }

    public void carsPassingNS() throws InterruptedException {


        synchronized (this){
            cars ++;
            System.out.println("This is how many cars have passed into the NS direction: " + cars);
            sem.release();
        }

        Thread.sleep(20);

        synchronized (this){
            cars --;
        }

    }

    public void connect1() throws InterruptedException {
        sem1.acquire();

        try{
            carsPassingWE();
        }finally {
            sem1.release();
        }
    }

    public void carsPassingWE() throws InterruptedException {


        synchronized (this){
            cars ++;
            System.out.println("This is how many cars have passed into the WE direction: " + cars);
            sem1.release();
        }

        Thread.sleep(20);

        synchronized (this){
            cars --;
        }

    }

}
