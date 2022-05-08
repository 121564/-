package b;
/*
线程安全问题
1.线程同步(同步代码块) 对出现问题的核心代码使用synchronized进行加锁
且每次只能1个线程占锁进入访问
 */
public class ThreadSafe1 {
    public static void main(String[] args) {
        synchronized ("共享资源作为锁对象"/* 此处应填this ，否则会影响其他无关线程*/) {
            //1.实例方法填 this   2.静态方法填 类名.class
            //操作共享资源的代码
            //核心代码
        }
    }
}
