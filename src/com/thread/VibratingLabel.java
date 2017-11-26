package com.thread;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

class VibeLabelRunnable implements Runnable {   // label을 진동시키는 스레드.
    private JLabel label;
    public VibeLabelRunnable(JLabel label) {  // 생성된 label의 주소를 참조.
        this.label = label;
    }
    @Override
    public void run() {
        double randomvalue;
        int homeX = label.getX();  // 원위치 x좌표
        int homeY = label.getY();  // 원위치 y좌표
        while(true) {
            randomvalue = Math.random();
            int x = (int)(randomvalue * 10) - 5;  // 최대 5의 진폭.
            randomvalue = Math.random();
            int y = (int)(randomvalue * 10) - 5;  // 최대 5의 진폭.
            label.setLocation(homeX + x,homeY + y);  // 원위치를 중심으로 진폭만큼 진동.
            try {
                sleep(50);   // 0.05초마다 진동.
            } catch (InterruptedException e) {
                return;    // 오류 발생시 스레드 종료.
            }
        }
    }
}
public class VibratingLabel extends JFrame{
    private static Thread th;
    private JLabel la;
    public VibratingLabel() {   // JFrame 초기 생성내용.
        setTitle("VibrationJFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();  // 프레임에 부착된 컨텐트 팬을 알아냄.
        la = new JLabel("진동 레이블");  // "진동 레이블" 이라고 표시되는 label 생성
        la.setLocation(100,50);   // 초기 위치.
        la.setFont(new Font("Gothic", Font.PLAIN, 20)); // font  설정.
        contentPane.add(la); // 컨텐트 팬에 label 부착.

        VibeLabelRunnable runnable = new VibeLabelRunnable(la); // 스레드 생성
        th = new Thread(runnable);  // runnable 객체의 스레드 생성.
        th.start(); // 스레드 시작.
        setSize(400, 300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new VibratingLabel();
    }
}
