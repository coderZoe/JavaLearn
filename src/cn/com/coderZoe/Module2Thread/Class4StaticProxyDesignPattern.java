package cn.com.coderZoe.Module2Thread;

/**
 * @author yhs
 * @date 2020/4/9 20:42
 * @description
 */
public class Class4StaticProxyDesignPattern {
    /*
    * 静态代理设计模式
    * 1.真实角色
    * 2.代理角色
    * 真实角色和代理角色都需要实现同一个接口
     */

    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();

        //发现和thread还有runnable接口的设计模式一模一样
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
    }
}

//共同接口
interface Mary{
    void happyMarry();
}

//真实角色
class You implements Mary{
    @Override
    public void happyMarry() {
        System.out.println("happy wedding");
    }
}

//代理角色
class WeddingCompany implements Mary{
    private Mary target;

    public WeddingCompany(Mary target) {
        this.target = target;
    }


    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void ready(){
        System.out.println("布置婚礼");
    }

    private void after(){
        System.out.println("婚礼结束");
    }

}

