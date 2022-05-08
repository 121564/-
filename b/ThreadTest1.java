package b;
/*
多线程的创建方式一 : 继承Thread实现
1.定义一个线程类继承Thread
2.重写run方法
3.new一个线程对象
4.调用start方法
注意事项 :
1.子线程必须在主线程之前
2.调用的是start方法,而不是run方法
 */
public class ThreadTest1 {
    public static void main(String[] args) {
     Thread t=new MyThread();
     t.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程执行输出"+i);
        }
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程执行输出"+i);
        }
    }
}
