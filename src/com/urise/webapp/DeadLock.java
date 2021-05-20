package com.urise.webapp;


class DeadLock {

    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        DeadLock.deadLock(lock1, lock2);
        DeadLock.deadLock(lock2, lock1);
    }

    public static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println(getNameThread() + " try to catch object of monitor " + lock1);
            synchronized (lock1) {
                System.out.println(getNameThread() + " monitor of object " + lock1 + " was caught");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getNameThread() + " try to catch object of monitor  " + lock2);
                synchronized (lock2) {
                    System.out.println(getNameThread() + " monitor of object " + lock2 + " was caught");
                }
            }
        }).start();
    }

    private static String getNameThread() {
        return Thread.currentThread().getName();
    }
}
