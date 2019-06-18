package com.example.demo.Interview1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第一个线程循环5次，接着第二个线程循环10次，
 * 接着第三个线程循环15次，然后又到第一个线程循环5次
 * 如此往复执行50次
 */
public class Test2 {

    public static void main(String[] args) {

        ThreadTest3 threadTest3 = new ThreadTest3();

        Thread t1 = new Thread(threadTest3);
        Thread t2 = new Thread(threadTest3);
        Thread t3 = new Thread(threadTest3);

        t1.start();
        t2.start();
        t3.start();

    }
}

class ThreadTest3 implements Runnable {

    private Lock lock = new ReentrantLock();
    private int j = 0;
    private int k = 1;

    Condition con1 = lock.newCondition();
    Condition con2 = lock.newCondition();
    Condition con3 = lock.newCondition();

    public void father() {
        lock.lock();

        try {
            while (j != 0)
                con1.await();
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread A..." + i);
            }
            j = (j + 1) % 3;
            con2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void son() {
        lock.lock();
        try {
            while (j != 1)
                con2.await();
            for (int i = 1; i <= 10; i++) {
                System.out.println("Thread B..." + i);
            }
            j = (j + 1) % 3;
            con3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void granson() {
        lock.lock();
        try {
            while (j != 2)
                con3.await();
            for (int i = 1; i <= 15; i++) {
                System.out.println("Thread C..." + i);
            }
            j = (j + 1) % 3;
            con1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            if (k == 1) {
                father();
                k = (k + 1) % 3;
            } else if (k == 2) {
                son();
                k = (k + 1) % 3;
            } else {
                granson();
                k = (k + 1) % 3;
            }
        }

    /*  father();
        son();
        granson();*/
    }
}