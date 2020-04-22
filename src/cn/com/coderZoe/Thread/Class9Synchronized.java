package cn.com.coderZoe.Thread;

/**
 * @author yhs
 * @date 2020/4/11 22:15
 * @description
 */
public class Class9Synchronized {
    /*
    * 笔记
    * 线程同步是解决线程安全的一种方法
    * 并发存在问题必须满足三个条件
    * 1.操作同一个对象(只有在改数据才涉及到线程安全问题，只是查询不需要考虑线程安全性)
    * 2.多个用户
    * 3.同一时间
    *
    * 线程同步其实是一种等待机制。多个用户同时访问同一对象时需要进入这个对象的等待池形成队列，前一个线程执行完毕下一个线程再启用
    * 当然线程同步会导致性能的下降，原因有三点：
    * 1.当一个线程运行时，他是持有锁的这会导致其他线程挂起
    * 2.加锁和释放锁会消耗资源
    * 3.若优先级高的线程等待一个优先级低的线程释放锁，那么优先级就会失效
     */

    public static void main(String[] args) {
        //synchronized可以修饰方法或块

        //1.修饰方法
        SaleTickets saleTickets = new SaleTickets("乘客");
//        while(true){
//            new Thread(saleTickets).start();
//        }

        //2.修饰块
        //synchronized(obj){}
        //其中这里的obj被称为同步监视器,obj可以为任何对象，但一般将共享资源作为同步监视器
        //在修饰方法中是无需指定同步监视器的，因为同步方法的同步监视器是this即该对象本身
        //一般来说多用syn块 少用syn方法，因为syn块锁的更精确同时效率也更高
        Account account = new Account(1000000);
        while (account.getMoney()>0){
            Thread person1 = new TakeMoney(account,100000,"取钱1号");
            Thread person2 = new TakeMoney(account,50000,"取钱2号");
            person1.start();
            person2.start();
        }

    }
}

class SaleTickets implements Runnable{
    private int number = 100;
    private String name;
    @Override
    public void run() {
        buyTickets();
    }

    //同步化
    private synchronized void buyTickets(){
        if(this.number > 0){
            this.number--;
            System.out.println(this.name+"买走了一张票 现在还剩"+this.number+"张票");
        }else {
            System.out.println("票卖完了不好意思");
        }
    }

    public SaleTickets(String name) {
        this.name = name;
    }
}


class TakeMoney extends Thread{
    private final Account account;
    private int drawingMoney;
    private int pocketMoney = 0;

    public int getPocketMoney() {
        return pocketMoney;
    }

    public TakeMoney(Account account, int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override


    public void run() {
        drawMoney();
    }

    private void drawMoney(){
        //提高性能
        if(this.account.getMoney()-drawingMoney<0){
            return;
        }
        synchronized (this.account){
            if(this.account.getMoney()-drawingMoney < 0){
                System.out.println("钱的数量不够了，不可以取");
            }else {
                this.account.setMoney(this.account.getMoney()-drawingMoney);
                this.pocketMoney += this.drawingMoney;
                System.out.println(currentThread().getName()+"取了"+this.drawingMoney+"块钱，"+"账户还剩"+this.account.getMoney()+"块钱");
            }
        }
    }
}

class Account{
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
