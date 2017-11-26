package com.thread;

import javax.swing.*;
import static java.lang.Thread.sleep;

class VibrationRunnable implements Runnable {   // 프레임을 진동시키는 스레드.
    private VibratingFrame vibe;
    public VibrationRunnable(VibratingFrame vibe) {  // 생성된 프레임의 주소를 가져옴.
        this.vibe = vibe;
    }
    @Override
    public void run() {
        double randomvalue;
        int homeX = vibe.getX();  // 원위치 x좌표
        int homeY = vibe.getY();  // 원위치 y좌표
        while(true) {
            randomvalue = Math.random();
            int x = (int)(randomvalue * 10) - 5;  // 최대 5의 진폭.
            randomvalue = Math.random();
            int y = (int)(randomvalue * 10) - 5;  // 최대 5의 진폭.
            vibe.setLocation(homeX + x,homeY + y);  // 원위치를 중심으로 진폭만큼 진동.
            try {
                sleep(50);   // 0.05초마다 진동.
            } catch (InterruptedException e) {
                return;    // 오류 발생시 스레드 종료.
            }
        }
    }
}
public class VibratingFrame extends JFrame{
    private static Thread th;
    public VibratingFrame() {   // JFrame 생성내용.
        setTitle("VibrationJFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 240);
        setLocation(400,400);
        setVisible(true);
    }
    public static void main(String[] args) {
        VibrationRunnable runnable = new VibrationRunnable(new VibratingFrame()); // 스레드 생성
        th = new Thread(runnable);  // runnable 객체의 스레드 생성.
        th.start(); // 스레드 시작.
    }
}
