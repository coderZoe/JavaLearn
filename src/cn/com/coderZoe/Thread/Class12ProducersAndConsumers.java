package cn.com.coderZoe.Thread;

/**
 * @author yhs
 * @date 2020/4/13 21:56
 * @description
 */
public class Class12ProducersAndConsumers {
    /*
    * 生产者和消费者
    * 生产者消费者模式是用来解决并发线程间的协作
    * 生产者和消费者问题：
    * 1.假设仓库中只能存放一件产品，生产者将生产出来的产品放入仓库，消费者将仓库中的产品取走消费
    * 2.如果仓库中没有产品，则生产者将产品放入仓库，否则停止生产并等待，直到仓库中的产品被消费者取走为止
    * 3.如果仓库中放有产品，则消费者可以将产品取走消费，否则停止消费并等待，直到仓库中再次放入产品为止。
    *
    * 上述问题解析：
    * 生产者和消费者都为一个线程，他们共享一个资源仓库
    * 对于生产者，没有生产产品之前要通知消费者等待 而生产了产品之后又需要马上通知消费者消费
    * 对于消费者，在消费后，要通知生产者已经消费结束，需要继续生产新产品以供消费
    * 可见 仅有syn控制同一个资源还不够 还需要线程间的通信 等待和通知
    *
    * 解决方法
    * 管程法：
    * 解耦 在生产者和消费者之间构造缓冲区，两者只能和缓冲区打交道
    *
    * 信号灯法：
    * 你停车走 你走车停
    *
    * Java提供的方法：
    * 等待 wait() 线程一直等待直到其他线程通知，与sleep不同 会释放资源锁
    * 通知 notify() 唤醒一个等待状态的线程
    * 这俩方法只能放在同步块或同步方法中 否则会抛出异常
     */

    public static void main(String[] args) {
        //1.管程法：
        //1.生产者线程 2.消费者线程 3.缓冲区 4.资源(生产和消耗的)
        Buffered buffered = new Buffered();
        new Thread(new Producer(buffered)).start();
        new Thread(new Consumer(buffered)).start();

        //2.信号灯法：
        //借助标志位来判别
        Resource2 resource2 = new Resource2();
        new Thread(new Producer2(resource2)).start();
        new Thread(new Consumer2(resource2)).start();

    }
}

//生产者线程
class Producer implements Runnable{
    private Buffered buffered;

    public Producer(Buffered buffered) {
        this.buffered = buffered;
    }

    @Override
    public void run() {
        System.out.println("开始生产--------->");
        for(int i = 0; i < 100; i++){
            buffered.push(new Resource(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------->生产结束");
    }
}
//消费者线程
class Consumer implements Runnable{
    private Buffered buffered;

    public Consumer(Buffered buffered) {
        this.buffered = buffered;
    }

    @Override
    public void run() {
        System.out.println("开始消费--------->");
        for(int i = 0; i < 100; i++){
            buffered.pop();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------->消费结束");
    }
}
//缓冲区
class Buffered{
    private Resource [] resources = new Resource[10];
    private int count = 0;

    //生产
    public synchronized void push(Resource resource){
        if(this.count==10){
            try {
                System.out.println("缓冲区满了 无法再生产 只能等待");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.resources[this.count] = resource;
        System.out.println("生产了"+resource.getId()+"号资源");
        this.count++;
        this.notifyAll();

    }

    //消费
    public synchronized Resource pop(){
        if(this.count==0){
            try {
                System.out.println("缓冲区没数据 无法消费 只能等待");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count--;
        System.out.println("消费了"+this.resources[this.count].getId()+"号资源");
        this.notifyAll();
        return this.resources[this.count];

    }
}
//资源
class Resource{
    private int id;

    public Resource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

//生产者线程
class Producer2 implements Runnable{
    private Resource2 resource2;

    public Producer2(Resource2 resource2) {
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产--------->");
        for(int i = 0; i < 100; i++){
            this.resource2.setId(i);
            this.resource2.push();
        }
        System.out.println("-------------->生产者生产结束");
    }
}

//消费者线程
class Consumer2 implements Runnable{
    private Resource2 resource2;

    public Consumer2(Resource2 resource2) {
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费--------->");
        for(int i = 0; i < 100; i++){
            this.resource2.pop();
        }
        System.out.println("-------------->消费者消费结束");
    }
}
class Resource2{
    private boolean flag = true;    //如果为真 表示生产者生产 消费者等待 否则 消费者消费 生产者等待
    private int id;

    public synchronized void push(){
        if(!this.flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("生产者在生产"+this.id);
        this.flag = !this.flag;
        this.notifyAll();
    }

    public synchronized void pop(){
        if(this.flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者在消费"+this.id);
        this.flag = !this.flag;
        this.notifyAll();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}


