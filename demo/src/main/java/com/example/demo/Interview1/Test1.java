package com.example.demo.Interview1;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 线程间通信 主要有5种情况：
 * 如何让两个线程依次执行？
 * 那如何让 两个线程按照指定方式有序交叉运行呢？
 * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
 * 三个运动员各自准备，等到三个人都准备好后，再一起跑
 * 子线程完成某件任务后，把得到的结果回传给主线程
 *
 * http://www.importnew.com/26850.html
 */
public class Test1 {

    /**
     * 线程A,B,C依次执行 使用线程的join()方法
     */
    public static void demo1(){
        Thread A = new Thread(()->{
            System.out.println("线程A:");
            for (int i=0; i<= 10; i=i+2){
                System.out.println("--"+i);
            }
        });
        Thread B = new Thread(()->{
            try {
                A.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程B:");
            for (int i=1; i<= 9; i=i+2){
                System.out.println("--"+i);
            }
        });
        Thread C = new Thread(()->{
            try {
                B.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程C");
        });
        A.start();
        B.start();
        C.start();
    }

    /**
     * 线程依次执行 使用synchronized关键字
     */
    public static void demo2(){
        Thread A = new Thread(()->{
           synchronized (Test1.class){
               System.out.println("1");
               System.out.println("2");
               System.out.println("3");
           }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Test1.class){
                    System.out.println("4");
                    System.out.println("5");
                    System.out.println("6");
                }
            }
        });
        A.start();
        B.start();
    }

    /**
     *
     * wiat/notify机制，两个线程按特定方式交叉执行
     * wait()：交出锁控制权，进入等待状态
     * notifyAll(),通知所有正在wait的线程运行;
     * notify();随机通知某个线程
     */
    public static void demo3() {
        Test1 object = new Test1();
        Thread A = new Thread(()->{
            synchronized (object){
                System.out.println("1:");
                System.out.println("2:");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3:");
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                     System.out.println("A");
                     System.out.println("B");
                     object.notifyAll();
                }
            }
        });
        A.start();
        B.start();
    }

    /**
     * 计数器CountDownLatch等待机制 count 等于0时触发执行：通常用于一个线程等到多个线程执行之后执行
     */
    public static void demo4(){
        int workCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(workCount);
        Thread D = new Thread(()->{
            try {
                System.out.println("Thread D is waiting ");
                countDownLatch.await();//计数器等于0时执行;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程D执行：");
        });
        Thread E = new Thread(()->{
            try {
                System.out.println("Thread E is waiting ");
                countDownLatch.await();//计数器等于0时执行;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程E执行：");
        });
        D.start();
        E.start();
        for(char threadName = 'a';threadName<='c';threadName++){
            final String  name = String.valueOf(threadName);
            new Thread(()->{
                System.out.println(name+" is working ");
                countDownLatch.countDown();
                    //countDownLatch.await();
            }).start();
        }


    }

    /**
     * CyclicBarrier 等待机制 等待线程 满足指定数量后触发 开始一起共同执行；
     */
    public static void demo5(){
        int runner = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        Random random = new Random();

        for (int i = 1;i<=10 ;i++ ){
            final int second = random.nextInt(10000);
            final int num = i;
            new Thread(()-> {
                try {
                    Thread.sleep(second);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNumber(num);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("线程一起共同执行-----------"+num);
            }).start();

        }
    }

    /**
     * 面试题：两个线程 线程1打印 0,2,4,6,8....100, 线程2打印1,3,5,7,9....99 ,最后结果打印 0,1,2,3,4,5,6....99,100
     *
     * 利用 Synchronized 关键字及wait/notify机制实现 线程之间循环交叉执行 输出 0,1,2,3,4,5......99,100
     */
    public static Boolean  flag = false;

    public static void demo6() {


        Test1 object = new Test1();
        Thread A = new Thread(()-> {
                synchronized (object){
                    for (int i= 0;i<= 100;i=i+2){
                        if(flag){
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print("--"+i);
                        flag = true;
                        object.notify();
                    }
                }
            });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i= 1;i<100;i=i+2){
                        if(!flag){
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print("--"+i);

                        flag = false;
                        object.notify();

                    }

                }
            }
        });
        A.start();
        B.start();
    }



    public static void printNumber(int num){
        System.out.println("");
        System.out.print("Thread "+num+" is preparing ");
        for (int i= 0;i<10;i++){
            System.out.print("--"+i);
        }
    }

    public static void main(String arg[]) throws InterruptedException {
        Test1.demo6();
    }



}
