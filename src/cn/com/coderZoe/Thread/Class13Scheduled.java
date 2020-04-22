package cn.com.coderZoe.Thread;

import java.util.*;

/**
 * @author yhs
 * @date 2020/4/14 21:07
 * @description
 */
public class Class13Scheduled {
    /*
    * 笔记
    * 任务定时调度
    * 比如某天或某年(每天或每年)某一时间点做某项任务
    * java.util.Timer 线程调度任务 任务可以一次执行或定期重复执行 它本身就是个线程
    * void schedule(TimerTask task, Date time)  在指定的时间安排指定的任务执行。
    * void schedule(TimerTask task, Date firstTime, long period) 从指定的时间开始,对指定的任务执行重复的固定延迟执行 。
    * void schedule(TimerTask task, long delay) 在指定的延迟之后安排指定的任务执行。
    * void schedule(TimerTask task, long delay, long period) 在指定的延迟之后开始,重新执行固定延迟执行的指定任务。
    * java.util.TimerTask 一个抽象类 本身实现了Runnable class a extends TimerTask 实现自己的run方法
     */

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(),2000,1000);
        Calendar date = new GregorianCalendar(2020,Calendar.APRIL,14,21,43);
        timer.schedule(new MyTask(),date.getTime(),2000);

    }
}
class MyTask extends TimerTask{
    @Override
    public void run() {
        System.out.println("上床睡觉");
    }
}
