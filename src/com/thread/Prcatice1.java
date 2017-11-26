package com.thread;

import java.util.Scanner;

public class Prcatice1 implements Runnable {
    public void printnum() throws InterruptedException { // 1부터 10까지를 출력.
        int i=1;
        while (i<=10) {
            System.out.print(i + " ");
            i++;
        }
    }
    @Override
    public void run() {
        System.out.print("스레드 실행 시작\n");
        try {            // 반드시 예외를 발생 시킬 수 있는 코드가 들어와야한다.
            printnum();
        } catch (InterruptedException e) {  // 예외 처리, 종료
            return;
        } finally {
            System.out.print("\n스레드 종료\n"); // 마지막에 실행.
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("아무 키나 입력>> ");
        sc.next();      // 아무 문자열을 입력하면 실행됨.
        Thread th = new Thread(new Prcatice1());
        th.start();
    }
}
