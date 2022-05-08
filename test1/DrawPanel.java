package com.dfbz.test1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


    public class DrawPanel extends JPanel implements MouseListener {
        public static final int MARGIN = 30;  //边距
        public static final int GRID_SPAN = 35;  //网格间距

        public static final int ROWS = 15;
        public static final int COLS = 15;         //横纵线数量

        private int x_index, y_index;
        private boolean isBlack = true;
        private Chess[] chessList = new Chess[(ROWS + 1) * (COLS + 1)];//装棋子的数组
        private int chessCount = 0;//棋子个数
        //游戏是否结束的标志
        private boolean gameOver = false;


        public DrawPanel() {
            super();
            this.setBackground(Color.PINK);//背景颜色

            this.addMouseListener(this);
        }


        @Override
        public void paint(Graphics g) {
            super.paint(g);

            //画棋盘-横线
            for (int i = 0; i <= ROWS; i++) {
                g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
            }
            //画棋盘-纵线
            for (int i = 0; i <= COLS; i++) {
                g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
            }

            //画棋子

            for (int i = 0; i < chessCount; i++) {
                int xPos = chessList[i].getX() * GRID_SPAN + MARGIN;
                int yPos = chessList[i].getY() * GRID_SPAN + MARGIN;
                g.setColor(chessList[i].getColor());
                g.fillOval(xPos - Chess.DIAMETER / 2, yPos - Chess.DIAMETER / 2, Chess.DIAMETER, Chess.DIAMETER);
                //最后一颗棋子上面有红色框
                if (i==chessCount-1){
                    g.setColor(Color.RED);
                    g.drawRect(xPos-Chess.DIAMETER/2,yPos-Chess.DIAMETER/2,Chess.DIAMETER,Chess.DIAMETER);
                }

            }


        }

        /**
         * getPreferredSize()重写，是设置当前的组件大小时最佳的大小，自动调用
         */


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(MARGIN * 2 + GRID_SPAN * ROWS, MARGIN * 2 + GRID_SPAN * COLS);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            x_index = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
            y_index = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
            System.out.println("(" + x_index + "," + y_index + ")");
            //判断是不是一个可用的棋子
            //1.游戏结束不能下棋子
            if (gameOver) {
                return;
            }
            //2.棋子落在棋盘外面不能下
            if (x_index < 0 || x_index > COLS || y_index < 0 || y_index > ROWS) {
                return;
            }
            //3.位置上已经有棋子存在，不能下
            if (findChess(x_index, y_index)) {
                return;
            }
            Chess ch = new Chess(x_index, y_index, isBlack ? Color.black : Color.white);
            chessList[chessCount++] = ch;
            System.out.println("棋子个数" + chessCount);
            this.repaint();
            // 判断赢棋
            if (isWin()) {
                String msg = String.format("恭喜您，%s赢了", isBlack ? "黑棋" : "白棋");
                JOptionPane.showMessageDialog(this, msg);
                gameOver = true;
            }
            isBlack = !isBlack;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        //位置上是否有棋子
        private boolean findChess(int x, int y) {
            for (Chess c : chessList) {
                if (c != null && c.getX() == x && c.getY() == y) {
                    return true;
                }
            }
            return false;
        }

        //得到棋盘上的棋子
        private Chess getChess(int x, int y, Color color) {
            for (Chess c : chessList) {
                if (c != null && c.getX() == x && c.getY() == y && c.getColor() == color) {
                    return c;
                }
            }
            return null;
        }

        //赢棋的话，需要四个方向
        private boolean isWin() {
            return search1()||search2()||search3()||search4();
        }

        //斜向-西南-东北方向
        private boolean search1() {
            int continueCount = 1;//连续棋子的个数
            //斜向上寻找
            for (int x = x_index + 1, y = y_index - 1; x <= COLS && y >= 0; x++, y--) {
                Color c = isBlack ? Color.black : Color.white;
                if (getChess(x, y, c) != null) {
                    continueCount++;
                } else {
                    break;
                }

            }
            //斜向下寻找
            for (int x = x_index - 1, y = y_index + 1; x >= 0 && y <= ROWS; x--, y++) {
                Color c = isBlack ? Color.black : Color.white;
                if (getChess(x, y, c) != null) {
                    continueCount++;
                } else {
                    break;
                }
            }
            //五子是不是连珠
            if (continueCount >= 5) {
                return true;
            } else {
                continueCount = 1;
            }

            return false;

        }
        //水平左右-西东
        private  boolean search2(){
            int continueCount = 1;//连续棋子的个数
            //寻找方向-西方向
            for (int x=x_index-1;x>=0;x--){
                Color c=isBlack?Color.black:Color.white;
                if (getChess(x,y_index,c)!=null){
                    continueCount++;
                }else {
                    break;
                }
            }
            //寻找-东方向
            for (int x=x_index+1;x<=COLS;x++) {
                Color c = isBlack ? Color.black : Color.white;
                if (getChess(x, y_index, c) != null) {
                    continueCount++;
                } else {
                    break;
                }
            }
            //五子是不是连珠
            if (continueCount >= 5) {
                return true;
            } else {
                continueCount = 1;
            }

            return false;

        }
        //斜向-西北-东南
        private boolean search3() {
            int continueCount = 1;//连续棋子的个数
            //斜向上寻找
            for (int x = x_index - 1, y = y_index - 1; x >0 && y >= 0; x--, y--) {
                Color c = isBlack ? Color.black : Color.white;
                if (getChess(x, y, c) != null) {
                    continueCount++;
                } else {
                    break;
                }

            }
            //斜向下寻找
            for (int x = x_index + 1, y = y_index + 1; x<= COLS && y <= ROWS; x++, y++) {
                Color c = isBlack ? Color.black : Color.white;
                if (getChess(x, y, c) != null) {
                    continueCount++;
                } else {
                    break;
                }
            }
            //五子是不是连珠
            if (continueCount >= 5) {
                return true;
            } else {
                continueCount = 1;
            }

            return false;

        }
        //垂直上下
        private  boolean search4(){
            int continueCount = 1;//连续棋子的个数
            //寻找方向-西方向
            for (int y=y_index-1;y>=0;y--){
                Color c=isBlack?Color.black:Color.white;
                if (getChess(x_index,y,c)!=null){
                    continueCount++;
                }else {
                    break;
                }
            }
            //寻找-下的方向
            for (int y=y_index+1;y<=ROWS;y++) {
                Color c = isBlack ? Color.black : Color.white;
                if (getChess(x_index, y, c) != null) {
                    continueCount++;
                } else {
                    break;
                }
            }
            //五子是不是连珠
            if (continueCount >= 5) {
                return true;
            } else {
                continueCount = 1;
            }

            return false;

        }

        //重新开始游戏的方法
        public void restartGamne() {
            //清除棋子
            for (int i = 0; i < chessList.length; i++) {
                chessList[i] = null;
            }//恢复游戏相关的变量
            isBlack = true;
            gameOver = false;
            chessCount = 0;
            this.repaint();
        }
        //悔棋的方法
        public void goback(){
            //棋盘中没有棋子不能悔棋
            if (chessCount==0){
                return;
            }
            chessList[chessCount-1]=null;
            chessCount--;
            if (chessCount>0){
                x_index=chessList[chessCount-1].getX();
                y_index=chessList[chessCount-1].getY();
            }
            isBlack=!isBlack;
            this.repaint();
        }

    }

