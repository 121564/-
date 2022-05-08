package com.kunang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int lenth;//蛇的长度
    int lenth2;//蛇的长度
    int[] snakeX = new int[600];//蛇的坐标X
    int[] snakeY = new int[500];//蛇的坐标Y
    int[] snake2X = new int[600];//蛇2的坐标X
    int[] snake2Y = new int[500];//蛇2的坐标Y
    String fx ; //R:右，L:左，U:上，D：下
    String fx2;//D:右，A:左，W:上，S：下
    boolean isStart = false;//游戏是否开始
    Timer timer = new Timer(200 ,this);
    //定义一个食物
    int foodx;
    int foody;
    Random random = new Random();
    //死亡判断
    boolean isFail = false;
    //积分系统
    int score;
    //构造器
    public GamePanel(){
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();//让时间动起来
    }
    //初始化
    public void init(){
        lenth = 2;lenth2 = 2;
        snakeX[0] = 100;snakeY[0] = 100;//头部坐标
        snake2X[0] = 775;snake2Y[0] = 100;//头部坐标
        snakeX[1] = 75;snakeY[1] = 100;//第一个身体坐标
        snake2X[1] = 800;snake2Y[1] = 100;//第一个身体坐标
//        snakeX[2] = 50;snakeY[2] = 100;//第二个身体坐标
        fx = "R";
        fx2 = "A";
        //生成食物
        food();
        foodin();
        score = 0;


    }
    //保证食物不出现在蛇的身体上
    public void foodin(){
        for (int i = 0; i < lenth; i++) {
            if (snakeX[i]==foodx&&snakeY[i]==foody){
                food();
                foodin();
            }
        }
        for (int i = 0; i < lenth2; i++) {
            if (snake2Y[i]==foodx&&snake2Y[i]==foody){
                foodin();
            }
        }
    }

    //随机生成食物
    public void food(){
        foodx = 25 + 25 *random.nextInt(34);
        foody = 75 + 25 *random.nextInt(24);}
    //画板： 画界面，画蛇,画食物，画积分
    //Graphics : 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.white);//设置背景颜色
        //绘制头部的广告栏
        Data.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,600);
        //画一个静态小蛇
        if (fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if (fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if (fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);//小蛇的身体是由lenth决定的
        }
        //画一条静态的小蛇2
        if (fx2.equals("D")){
            Data.right.paintIcon(this,g,snake2X[0],snake2Y[0]);
        }else if (fx2.equals("A")){
            Data.left.paintIcon(this,g,snake2X[0],snake2Y[0]);
        }
        else if (fx2.equals("S")){
            Data.down.paintIcon(this,g,snake2X[0],snake2Y[0]);
        }
        else if (fx2.equals("W")){
            Data.up.paintIcon(this,g,snake2X[0],snake2Y[0]);
        }
        for (int i = 1; i < lenth2; i++) {
            Data.body.paintIcon(this,g,snake2X[i],snake2Y[i]);//小蛇的身体是由lenth决定的
        }
        //画食物
        Data.food.paintIcon(this,g,foodx,foody);
        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度:"+(lenth-1),750,35);
        g.drawString("分数:"+score,750,50);

        //游戏提示:是否开始
        if (isStart==false){
            //画一个文字，String
            g.setColor(Color.white);//设置画笔的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD,40));//设置字体
            g.drawString("按下空格开始游戏",300,300);
        }
        //失败提醒
        if(isFail){
            //画一个文字，String
            g.setColor(Color.red);//设置画笔的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD,40));//设置字体
            g.drawString("游戏失败，按下空格重新开始",200,300);
        }

    }


    //接受键盘的输入:监听
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE){//如果按下的是空格键
            if (isFail){
                //失败，游戏再来一遍
                isFail = false;
                init();//重新初始化游戏
            }else{
                isStart = !isStart;
            }

            repaint();//刷新界面
        }
        //键盘控制走向
        if (keyCode ==KeyEvent.VK_LEFT){
            fx="L";
        }else if (keyCode ==KeyEvent.VK_RIGHT){
            fx="R";
        }else if (keyCode ==KeyEvent.VK_UP){
            fx="U";
        }else if (keyCode ==KeyEvent.VK_DOWN){
            fx="D";
        }
        //键盘控制小蛇2的走向
        //键盘控制走向
        else if (keyCode ==KeyEvent.VK_A){
            fx2="A";
        }else if (keyCode ==KeyEvent.VK_D){
            fx2="D";
        }else if (keyCode ==KeyEvent.VK_W){
            fx2="W";
        }else if (keyCode ==KeyEvent.VK_S){
            fx2="S";
        }
    }
    //定时器，监听时间，帧:执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有结束
        if (isStart&&isFail==false){
            for (int i = lenth-1; i > 0 ; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];}
                //通过控制方向让头部移动
                if (fx.equals("R")){
                    snakeX[0] = snakeX[0] + 25;//头部移动
                    //边界判断
                    if (snakeX[0]>=850){isFail=true;}
                }else if (fx.equals("L")){
                    snakeX[0] = snakeX[0] - 25;//头部移动
                    if (snakeX[0]<=25){isFail=true;}
                }else if (fx.equals("U")){
                    snakeY[0] = snakeY[0] - 25;//头部移动
                    if (snakeY[0]<=75){isFail=true;}
                }else if (fx.equals("D")){
                    snakeY[0] = snakeY[0] + 25;//头部移动
                    if (snakeY[0]>=650){isFail=true;}
                }
                //如果小蛇吃到食物
            if (snakeX[0]==foodx&&snakeY[0]==foody){
                lenth++;
                score=score + 100;
                //重新生成食物,如果出现在小蛇的身上就再次随机生成
                food();
                foodin();
            }
            //结束判断
            for (int i = 1;i<lenth;i++){
                if (snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                    isFail=true;
                }
            }
                repaint();//刷新界面
            //小蛇2通过键盘移动起来
            for (int i = lenth2-1; i > 0 ; i--) {
                snake2X[i] = snake2X[i - 1];
                snake2Y[i] = snake2Y[i - 1];}
            //通过控制方向让头部移动
            if (fx2.equals("D")){
                snake2X[0] = snake2X[0] + 25;//头部移动
                //边界判断
                if (snake2X[0]>=850){isFail=true;}
            }else if (fx2.equals("A")){
                snake2X[0] = snake2X[0] - 25;//头部移动
                if (snake2X[0]<=25){isFail=true;}
            }else if (fx2.equals("W")){
                snake2Y[0] = snake2Y[0] - 25;//头部移动
                if (snake2Y[0]<=75){isFail=true;}
            }else if (fx2.equals("S")){
                snake2Y[0] = snake2Y[0] + 25;//头部移动
                if (snake2Y[0]>=650){isFail=true;}
            }
            //如果小蛇2吃到食物
            if (snake2X[0]==foodx&&snake2Y[0]==foody){
                lenth2++;
                score=score + 100;
                //重新生成食物,如果出现在小蛇的身上就再次随机生成
                food();
                foodin();
            }
            //结束判断
            for (int i = 1;i<lenth2;i++){
                if (snake2X[0]==snake2X[i]&&snake2Y[0]==snake2Y[i]){
                    isFail=true;
                }
            }
            repaint();//刷新界面
            }

        timer.start();//让时间动起来
        }














    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起:
    }
    @Override
    public void keyReleased(KeyEvent e) {
//释放某个键
    }}


