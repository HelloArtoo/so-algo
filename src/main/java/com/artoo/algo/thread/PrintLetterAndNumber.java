package com.artoo.algo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程，一个打印数字，一个打印字母，输出：1A2B...26Z
 */
public class PrintLetterAndNumber {


    //wait 和 notify
    private static Object monitor = new Object();

    private static void print1() throws InterruptedException {
        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    try {
                        monitor.notify();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    monitor.notify();
                }
                monitor.notify();
            }
        }).start();


        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 26; i++) {
                    try {
                        System.out.print((char) ('A' + i));
                        monitor.notify();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                monitor.notify();
            }
        }).start();

    }


    //LockSupport的用法
    static Thread t1 = null, t2 = null;

    private static void print2() throws InterruptedException {

        t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });


        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                LockSupport.park();
                System.out.print((char) ('A' + i));
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }

    //wait 和 notify
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();

    private static void print3() throws InterruptedException {

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    c2.signal();
                    c1.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i));
                    c1.signal();
                    c2.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

    }

    public static void main(String[] args) throws InterruptedException {
        print3();
    }
}
