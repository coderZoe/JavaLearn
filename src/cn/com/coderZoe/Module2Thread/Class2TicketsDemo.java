package cn.com.coderZoe.Module2Thread;

/**
 * @author yhs
 * @date 2020/4/8 21:47
 * @description
 */
public class Class2TicketsDemo {
    public static void main(String[] args) {

        //抢票 但是这样写会有线程安全问题
        //对于网络延迟就会有问题

        //一份资源
        Runnable runnable = new Tickets();

        //三个代理
//        new Thread(runnable,"代理1").start();
//        new Thread(runnable,"代理2").start();
//        new Thread(runnable,"代理3").start();

        //同样是上面的情况 如果加了网络延迟，就能看出问题
        Runnable runnable2 = new DelayTickets();

        //三个代理
        new Thread(runnable2,"代理1").start();
        new Thread(runnable2,"代理2").start();
        new Thread(runnable2,"代理3").start();
    }
}

class Tickets implements Runnable{
    private int ticketsNum = 100;

    @Override
    public void run() {
        while(true){
            if(this.ticketsNum<=0){
                break;
            }
            System.out.println("当前代理:"+Thread.currentThread().getName()+"还剩余"+--this.ticketsNum);
        }
    }
}

class DelayTickets implements Runnable{
    private int ticketsNum = 100;

    @Override
    public void run() {
        while(true){
            if(this.ticketsNum<=0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前代理:"+Thread.currentThread().getName()+"取走了一张票，还剩余"+--this.ticketsNum);
        }
    }
}
