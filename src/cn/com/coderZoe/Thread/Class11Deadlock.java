package cn.com.coderZoe.Thread;

/**
 * @author yhs
 * @date 2020/4/13 21:26
 * @description
 */
public class Class11Deadlock {
    /*
    * 死锁
    * 过多的同步可能造成相互不释放资源从而相互等待，一般发生于同步中持有多个对象的锁
    * 解决方法，同时只锁一个资源 锁一个用完就释放一个
    * 或者将多个资源整合为一个资源 然后锁这个总的资源 但这样效率应该更低一点
     */

    public static void main(String[] args) {
        Makeup makeup = new Makeup(0);
        Makeup makeup1 = new Makeup(1);
        new Thread(makeup,"小红").start();
        new Thread(makeup1,"小芳").start();
    }
}

//口红资源
class LipsTick{

}
class Mirror{

}
class Makeup implements Runnable{
    //将资源放进来

    private static final LipsTick LIPS_TICK = new LipsTick();
    private static final Mirror MIRROR = new Mirror();
    private int choice;

    public Makeup(int choice) {
        this.choice = choice;
    }

    @Override
    public void run() {
        if(this.choice==0){
            synchronized (Makeup.LIPS_TICK){
                try {
                    System.out.println(Thread.currentThread().getName()+"正在用口红，还想要镜子");
                    Thread.sleep(1000);
                    synchronized (Makeup.MIRROR){
                        System.out.println(Thread.currentThread().getName()+"正在用口红和镜子");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else {
            synchronized (Makeup.MIRROR){
                try {
                    System.out.println(Thread.currentThread().getName()+"正在用镜子，还想要口红");
                    Thread.sleep(1000);
                    synchronized (Makeup.LIPS_TICK){
                        System.out.println(Thread.currentThread().getName()+"正在用口红和镜子");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
