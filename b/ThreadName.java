package b;
/*
区分线程执行对象
1.得到线程名称 .getName()
2.设置线程名称 .setName()/Thread(String)/Thread(Runnable,String)
3.获取当前线程 .currentThread()
4.让线程进入休眠状态 .sleep(long) 单位毫秒

 */
public class ThreadName {
    public static void main(String[] args) {
        Thread t1=new MyThread1("1号");
        t1.start();
        //t1.getName();

        Thread t2=new MyThread1("2号");
        t2.start();
        //t2.setName("2号线程");
        for (int i = 0; i < 5; i++) {
            System.out.println("main线程执行:"+i);
        }
    }
}
class MyThread1 extends Thread{
    public MyThread1(String name) {
        //为当前线程对象设置名称
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
    }
}