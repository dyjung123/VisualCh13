package com.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BubbleProduction extends JFrame{
    Container c;
    BubbleProduction() {
        setTitle("Bubble Production");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.addMouseListener(new MouseAdapter() {  // 마우스 클릭 이벤트를 처리하기 위한 MouseAdapter 생성
            @Override
            public void mouseClicked(MouseEvent e) {
                Bubble bubble = new Bubble(new ImageIcon("images/balloons.png")); // 클릭시 풍선생성
                bubble.setSize(32,32);    // 사이즈 결정
                bubble.setLocation(e.getX(),e.getY());  // 마우스 클릭위치에 생성
                c.add(bubble);      // 컨테이너에 부착
                c.repaint();        // 부착된 label이 보이도록 repaint() 메소드 호출
            }
        });
        setSize(500,360);
        setVisible(true);
    }
    class Bubble extends JLabel implements Runnable {
        public Bubble(ImageIcon image) {
            super(image);  // 전달받은 image로 label 초기화
            Thread th = new Thread(this);  // 풍선이 위로 올라가게하는 Thread 생성, 실행
            th.start();
        }
        @Override
        public void run() {
            int xPosition = this.getX();   // 풍선의 원래 위치를 파악
            int yPosition = this.getY();   // 풍선의 원래 위치를 파악
            while (true) {
                yPosition -= 5;   // 5의 속도로 움직임
                this.setLocation(xPosition,yPosition);
                if (yPosition < -32) {  // 프레임 바깥으로 나가면 풍선 label 삭제, thread 종료.
                    c.remove(this);
                    break;
                }
                try {
                    Thread.sleep(20);  // 0.02초마다 움직임
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
    public static void main(String[] args) {
        new BubbleProduction();
    }
}
