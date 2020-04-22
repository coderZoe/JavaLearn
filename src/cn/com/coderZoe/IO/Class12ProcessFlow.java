package cn.com.coderZoe.IO;

/**
 * @author yhs
 * @date 2020/4/2 16:43
 * @description
 */
public class Class12ProcessFlow {
    /*
    * 之前写的都是节点流 节点流是直接与数据源打交道 现在学习处理流 处理流是用于提升流的输入输出性能
    * 装饰器设计模式
    * 装饰器设计模式包含四个组件
    * 1.抽象组件：需要装饰的抽象对象或接口
    * 2.具体组件：具体需要装饰的对象 继承1的类或接口
    * 3.抽象装饰类 用来装饰装饰对象的，里面数据域必须包含装饰对象以及与装饰对象相同的方法（也需要继承1的接口）
    * 4.具体装饰类 用来装饰对象 继承自3
     */

    public static void main(String[] args) {
        //装饰器模式
        //实现放大器对人声音放大的功能

        //创建一个人
        Person person = new Person(10);
        person.say();

        //将人进行装饰 放入放大器中
        Amplifier amplifier = new Amplifier(person);
        amplifier.say();

        //模拟咖啡
        Drink coffee = new Coffee();
        Milk milk = new Milk(coffee);
        System.out.println(milk.info());
        System.out.println(milk.cost());

        Sugar sugar = new Sugar(coffee);
        System.out.println(sugar.info());
        System.out.println(sugar.cost());

        //再次装饰
        Sugar sugar1 = new Sugar(milk);
        System.out.println(sugar1.info());
        System.out.println(sugar1.cost());
    }
}

interface say{
    void say();

}
class Person implements say{
    private int voice;

    @Override
    public void say() {
        System.out.println("人的声音为"+this.getVoice());
    }


    public Person(){

    }

    public Person(int voice){
        this.voice = voice;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }
}
class Amplifier implements say{
    private Person person;

    @Override
    public void say() {
        System.out.println("人的声音为"+this.person.getVoice()*100);
    }

    public Amplifier(){

    }

    public Amplifier(Person person){
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

//抽象组件
interface Drink{
    //费用
    double cost();
    String info();
}

//具体组件
class Coffee implements Drink{

    private String name = "原味咖啡";
    private int cost = 10;
    @Override
    public double cost() {
        return this.cost;
    }

    @Override
    public String info() {
        return this.name;
    }
}


//抽象装饰类
abstract class Decorate implements Drink{
    private Drink drink;

    @Override
    public double cost() {
        return this.drink.cost();
    }

    @Override
    public String info() {
        return this.drink.info();
    }

    public Decorate(Drink drink){
        this.drink = drink;
    }
}

//具体装饰类
class Milk extends Decorate{
    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return super.cost()*4;
    }

    @Override
    public String info() {
        return super.info()+"加入了牛奶";
    }
}

//具体装饰类
class Sugar extends Decorate{
    public Sugar(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return super.cost()*2;
    }

    @Override
    public String info() {
        return super.info()+"加入了蔗糖";
    }
}


