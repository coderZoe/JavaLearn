package cn.com.coderZoe.Module2Thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author yhs
 * @date 2020/4/8 20:25
 * @description
 */
public class Class1CreateThread {
    /*
    * 笔记
    * 创建线程 三种方法
    * 继承Thread方法，重写run方法
    * 实现Runnable接口，重写run方法
    * 实现Callable接口 重写call方法(了解即可)
    *
    * 这里解释一个内容：
    * 多线程的调用是由任务调度器也就是cpu决定的，你将线程丢给cpu，cpu再决定是否运行线程
    * 所有线程的开始是ThreadObj.start()，而不是Runnable.run()，因为如果直接调用run方法就是直接执行了
    * 而调用start方法其实就是在跟cpu说，你可以执行了，但你到底调不调run方法在你自己，我管不着
    * start方法是Thread类的方法，同时Thread类也实现了runnable接口
    * 所以当一个类继承Thread，重写run方法时，只需要自己调用start，因为他自己就是Thread(Thread子类 自然也有start方法)
    * 但当一个类实现runnale接口时，需要创建那么一个Thread类，用这个创建的对象来调用start方法，
    * 即这个创建的Thread类就是为了调用start方法，将线程提交给CPU
    * 在我们编程时更多的使用接口实现，原因是java是单继承，如果继承了Thread就没法继承别的了
    * Thread本身还有一个静态方法sleep,Thread.sleep(long ms)
     */

    public static void main(String[] args) {
        //方法一：继承Thread类
       StartThread startThread = new StartThread();
       startThread.start();

       //多线程下载多个资源点文件
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileUtils.copyURLToFile(new URL("https://www.baidu.com"),new File("百度.html"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileUtils.copyURLToFile(new URL("http://www.163.com"),new File("网易.html"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileUtils.copyURLToFile(new URL("https://www.qq.com/"),new File("腾讯.html"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread1.start();
        thread2.start();
    }
}

class StartThread extends Thread{

    //重写run()
    @Override
    public void run() {
        System.out.println("线程运行");;
    }
}