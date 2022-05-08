package b;

import sun.net.NetworkServer;

/*
Runnable创建方式的另一种写法(匿名内部类实现)
 */
public class ThreadTest2Other {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("子线程1执行输出"+i);
                }
            }
        }).start();

        new Thread(() ->{
                for (int i = 0; i < 5; i++) {
                    System.out.println("子线程2执行输出"+i);
                }
        }).start();

        for (int i = 0; i < 5; i++) {
            System.out.println("主线程执行输出"+i);
        }
    }
}