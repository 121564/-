package b;
/*
多线程创建方式二 :
1.定义一个线程任务类,实现Runnable接口
2.重写run方法
3.创建一个任务对象
4.把任务对象交给Thread处理
5.启动线程
有点:可以继续继承类和实现接口,扩展性强
缺点:如果线程有执行结果不可以直接返回
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        Runnable a=new MyRunnable1();
        Thread t=new Thread(a);
        t.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程执行输出"+i);
        }
    }
}
class MyRunnable1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程执行输出"+i);
        }
    }
}
