package com.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;

class MyPanel extends JPanel { // 랜덤한 위치에 지름 50픽셀의 원을 그리는 MyPanel 클래스
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // JPanel에 구현된 paintComponent() 호출
        double randomevalue = Math.random();
        int x = (int)(randomevalue * 200) + 50;  // 랜덤위치 x 결정 (50~ 250)
        randomevalue = Math.random();
        int y = (int)(randomevalue * 170) + 50;  // 랜덤위치 y 결정 (50 ~ 220)
        g.setColor(new Color(0x00ff00ff));   // 색깔 보라색.
        g.drawOval(x,y,50,50);       // 원 그려서 생성.
    }
}
class CircleRunnable implements Runnable {  // 0.4초마다 panel을 새로그리는 스레드.
    private MyPanel panel;
    public CircleRunnable(MyPanel panel) {   // 만들어진 panel의 주소 참조.
        this.panel = panel;
    }
    @Override
    public void run() {      // 0.4초 간격으로 임의의 위치에 원을 그림.
        while (true) {
            panel.repaint();   // 패널의 paint() 메소드를 다시 실행.
            try {
                sleep(400);   // 0.4초간 대기
            } catch (InterruptedException e) {
                return;           // InterruptedException 발생시 스레드 종료.
            }
        }
    }
}
public class MoveCircle extends JFrame {
    private Thread th;
    private boolean noExcutedThread = true;  // 실행중인 스레드가 없으면 true, 있으면 false
    MyPanel panel;
    public MoveCircle() {
        setTitle("400ms 마다 원 이동");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MyPanel();  // MyPanel타입의 panel을 생성, 원이 그려진 패널.
        panel.addMouseListener(new MouseAdapter() { // panel에 마우스 이벤트를 처리하는 마우스 어댑터 구현.
            @Override
            public void mouseClicked(MouseEvent e) {
                if (noExcutedThread) { // 실행중인 스레드가 없으면 스레드 생성후 실행.
                    noExcutedThread = false;
                    CircleRunnable runnable = new CircleRunnable(panel);
                    th = new Thread(runnable); // CircleRunnable 스레드 생성
                    th.start();                // 스레드 시작.
                } else {                // 실행중인 스레드가 있으면 스레드 종료.
                    noExcutedThread = true;
                    th.interrupt();            // 스레드를 종료시키기 위한 exception 발생.
                }
            }
        });
        setContentPane(panel);  // 생성한 panel 패널을 컨텐트팬으로 사용.
        setSize(400, 330);  // JFrame의 크기 설정.
        setVisible(true);                // JFrame 표시.
        panel.requestFocus();            // panel이 이벤트를 받게함.
    }
    public static void main(String[] args) {
        new MoveCircle();   // JFrame 생성.
    }
}
