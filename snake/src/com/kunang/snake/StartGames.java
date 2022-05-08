package com.kunang.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //1.绘制一个静态窗口 JFrame
        JFrame frame = new JFrame("肖凯升和邓航的贪吃蛇");
        frame.setBounds(10,10,900,720);//设置界面大小
        frame.setResizable(false);//窗口大小则不可改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭事件，游戏可以关闭了。

        //2.面板 JPanel 可以加入到JFrame
        frame.add(new GamePanel());


        frame.setVisible(true);//让窗口可以展现出来
    }
}
