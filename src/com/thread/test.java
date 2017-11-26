//이론문제 테스트
package com.thread;

public class test implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            return;
        }
    }
    public static void main(String[] args) {
        Thread th = new Thread(new test());
        th.start();
    }
}
