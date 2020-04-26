package cn.com.coderZoe.Module2Thread;

/**
 * @author yhs
 * @date 2020/4/11 21:52
 * @description
 */
public class Class8ThreadClassification {
    /*
    * 线程分类 一种是默认的用户线程，一种是为用户线程服务的守护线程(后台日志记录、内存监控)
    * JVM需要确保用户线程执行完毕，而不用等待守护线程执行完毕
    * thread.setDaemon(true) 将线程设备守护线程
     */

    public static void main(String[] args) {
        //守护线程：为用户线程服务的，同时JVM停止不用等待守护线程执行完毕
        People you = new People();
        God god = new God();
        Thread t = new Thread(you);
        Thread t1 = new Thread(god);
        t1.setDaemon(true);
        t.start();
        t1.start();
    }
}


//用户线程
class People implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 365*100; i++){
            System.out.println("happy life");
        }
        System.out.println("life end");
    }
}

//守护线程
class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("God bless you");
        }
    }
}
