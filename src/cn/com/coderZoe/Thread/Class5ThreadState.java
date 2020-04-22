package cn.com.coderZoe.Thread;

/**
 * @author yhs
 * @date 2020/4/9 21:14
 * @description
 */
public class Class5ThreadState {
    /*
    * 笔记
    * 线程的状态
    * 一个线程在他的生命周期有5大状态
    * 1.新生状态 new Thread()
    * 2.就绪状态 调用start()方法后 立即进入就绪状态等待被调用 就绪状态只是表示有了运行的条件，不一定就被运行
    * 3.运行状态 调用run()方法进行执行
    * 4.阻塞状态
    * 5.死亡状态
    *
    * 两点注意事项：
    * 1.线程阻塞之后进入就绪状态等待被重新调用而不是直接进入运行状态
    * 2.线程死亡后无法再次运行
    *
    * 线程进入就绪状态有四种原因
    * 1.thread.start()之后 直接进入就绪态
    * 2.线程阻塞 解除阻塞后进入就绪状态
    * 3.调用yield方法，主动让出CPU,线程重新进入就绪状态
    * 4.JVM将CPU从该线程切换到其他线程
    *
    * 阻塞状态的发生有4种原因
    * 1.Thread.sleep()
    * 2.Thread.wait() wait()与sleep()的区别是wait()不占用线程资源和，sleep()会占用线程资源
    * 3.join() 阻塞指定到另一个线程完成以后再继续执行
    * 4.某些操作会进入阻塞 比如IO操作
    *
    * 线程的终止
    * 1.线程正常执行完毕 进入死亡状态
    * 2.人为终止线程的执行 stop和destroy方法可以 但不推荐使用
     */

    public static void main(String[] args) {
        //人为终止线程
        //1.加入线程运行标识
        Study study = new Study();
        new Thread(study).start();

        long startTime = System.currentTimeMillis();
        while(true){
            //让线程运行10s终止
            if(System.currentTimeMillis()-startTime>10000){
                study.setFlag(false);
                break;
            }
        }


    }
}
class Study implements Runnable{
    private boolean flag = true;
    private int i = 0;
    @Override
    public void run() {
        while (this.flag){
            System.out.println("正在学习...."+(++i));
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
