package com.pigcanfly.advance;

/**
 * @author tobbyquinn
 * @date 2019/09/09
 */
public class NoVisibility {

    private static volatile boolean ready;
    private static int number;
    private static long endTime;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            endTime = System.nanoTime();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReaderThread readerThread = new ReaderThread();
        readerThread.start();
        number = 42;
        long startTime = System.nanoTime();
        ready = true;
        while(readerThread.isAlive()){}
        System.out.println(endTime-startTime);
    }
}
