package cn.com.coderZoe.Module2Thread;

/**
 * @author yhs
 * @date 2020/4/11 11:41
 * @description
 */
public class Class7Priority {
    /*
    * 线程优先级
    * 通过设置线程优先级，会优先调度优先级高的线程，但到底怎么调看CPU，不一定优先度高就先执行
    * 线程的优先级用数字表示 大小从1到10
    * Thread.MIN_PRIORITY = 1 Thread.MAX_PRIORITY = 10 Thread.NORM_PRIORITY = 5
    * 通过方法thread.setPriority(int priority)来设置线程优先级
     */

    public static void main(String[] args) {
        Thread t1 = new Thread(new PriorityTest(),"线程1");
        t1.setPriority(10);
        Thread t2 = new Thread(new PriorityTest(),"线程2");
        t2.setPriority(8);
        Thread t3 = new Thread(new PriorityTest(),"线程3");
        t3.setPriority(6);
        Thread t4 = new Thread(new PriorityTest(),"线程4");
        t4.setPriority(4);
        Thread t5 = new Thread(new PriorityTest(),"线程5");
        t5.setPriority(2);

        t5.start();
        t4.start();
        t3.start();
        t2.start();
        t1.start();
    }
}
class PriorityTest implements Runnable{
    @Override
    public void run() {
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"--->优先级："+Thread.currentThread().getPriority());
    }
}

