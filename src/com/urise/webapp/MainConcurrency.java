package com.urise.webapp;

import com.urise.webapp.util.LazySingleton;
import sun.awt.windows.ThemeReader;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
      //          throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

//        Object lock1 = new Object();
//        Object lock2 = new Object();
        String lock1 = "lock1";
        String lock2 = "lock2";
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);

//        ThreadDemo1 T1 = new ThreadDemo1();
//        ThreadDemo2 T2 = new ThreadDemo2();
//        T1.start();
//        T2.start();

    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" потытка захватить объект монитора " + lock1);
                synchronized (lock1) {
                    System.out.println(" монитор объекта " + lock1 + " захвачен");
                    System.out.println(" потытка захватить объект монитора " + lock2);
                    synchronized (lock2) {
                        System.out.println("монитор объекта " + lock2 + " захвачен");
                    }
                }
            }
        }).start();
    }

//    private static class ThreadDemo1 extends Thread {
//        public void run() {
//            synchronized (lock1) {
//                System.out.println("Thread 1: Holding lock 1...");
//                try { Thread.sleep(10); }
//                catch (InterruptedException e) {}
//                System.out.println("Thread 1: Waiting for lock 2...");
//                synchronized (lock2) {
//                    System.out.println("Thread 1: Holding lock 1 & 2...");
//                }
//            }
//        }
//    }
//    private static class ThreadDemo2 extends Thread {
//        public void run() {
//            synchronized (lock2) {
//                System.out.println("Thread 2: Holding lock 2...");
//                try { Thread.sleep(10); }
//                catch (InterruptedException e) {}
//                System.out.println("Thread 2: Waiting for lock 1...");
//                synchronized (lock1) {
//                    System.out.println("Thread 2: Holding lock 1 & 2...");
//                }
//            }
//        }
 //   }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }
}
