package cn.com.coderZoe.Module2Thread;

/**
 * @author yhs
 * @date 2020/4/9 21:48
 * @description
 */
public class Class6ThreadBlocking {
    /*
    * 线程的阻塞
     * 阻塞状态的发生有4种原因
     * 1.Thread.sleep()
     * 2.Thread.wait() wait()与sleep()的区别是wait()不占用线程资源和，sleep()会占用线程资源
     * 3.join() 阻塞指定到另一个线程完成以后再继续执行
     * 4.某些操作会进入阻塞 比如IO操作
     */

    public static void main(String[] args) throws InterruptedException {

        //1.Thread.sleep()
        //sleep长用于模拟网络延迟或倒计时等功能
        //在sleep时间达到后 线程进入就绪态等待被CPU调用
        //sleep占用线程资源 不会释放锁

        //2.Thread.yield() 礼让线程
        //yield也是暂停线程，不同于sleep,sleep是有运行状态进入阻塞状态，sleep时间结束再由阻塞状态进入就绪状态
        //yield直接有运行状态进入就绪状态,高风亮节 让出CPU资源 避免当前线程占用CPU过久
        new Thread(new yieldTest(),"a").start();
        new Thread((new yieldTest()),"b").start();

        //3.join() thread.join()
        //join方法用于合并线程(插队线程)待此线程执行完成后，再执行其他其他线程
        //下面的方法是在main线程中创建一个新的线程运行，然后在main中调入新建线程的插队
        //此时main被阻塞，等新建线程运行结束后，main才再次运行(在哪个线程运行其他线程的插队，这个线程就被阻塞)
        //在main线程遍历至50之前，t线程和main线程轮流执行互相争抢CPU，但遍历到50时,t线程插队 main被阻塞，t运行完main才运行
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++ ){
                    System.out.println("新开线程"+i);
                }
            }
        });
        t.start();
        for(int i = 0 ; i < 100; i++){
            if(i==50){
                t.join();
            }
            System.out.println("main线程"+i);
        }

    }
}
class yieldTest implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"结束");
    }
}
