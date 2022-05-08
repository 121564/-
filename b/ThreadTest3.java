package b;
/*
多线程创建方式三:(解决了前两种方式无法直接返回计算结果的弊端(调用.get()方法))
1.定义一个任务类,实现Callable接口
2.重写call方法
3.创建Callable任务对象
4.把Callable对象交给FutureTask对象处理
5.交给线程处理
6.启动线程
 */
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadTest3 {
    public static void main(String[] args) {
        Callable<String> a1=new MyCallalbe(100);
        FutureTask<String> b1=new FutureTask<>(a1);
        Thread c1=new Thread(b1);
        c1.start();

        Callable<String> a2=new MyCallalbe(10);
        FutureTask<String> b2=new FutureTask<>(a2);
        Thread c2=new Thread(b2);
        c2.start();

        try {
            System.out.println("第一个结果"+b1.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println("第二个结果"+b2.get());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
class MyCallalbe implements Callable<String>{
    private int n;

    public MyCallalbe(int n) {
        this.n = n;
    }
    @Override
    public String call() throws Exception {
        int sum=0;
        for (int i = 0; i <= n; i++) {
            sum+=i;
        }
        return "子线程执行的结果是:"+sum;
    }
}