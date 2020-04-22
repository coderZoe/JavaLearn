package cn.com.coderZoe.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yhs
 * @date 2020/4/12 12:17
 * @description
 */
public class Class10CinemaDemo {
    /*
    * demo 电影院看电影
     */

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        Customer person1 = new Customer(cinema,"客户1",new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));
        Customer person2 = new Customer(cinema,"客户2",new ArrayList<Integer>(Arrays.asList(1,2,3,7)));
        new Thread(person1).start();
        new Thread(person2).start();

    }
}

class Cinema{
    private List<Integer> setList = new ArrayList<>();

    public Cinema() {
        for(int i = 0; i < 100; i++){
            this.setList.add(i);
        }
    }

    public List<Integer> getSetList() {
        return setList;
    }

    public void setSetList(List<Integer> setList) {
        this.setList = setList;
    }
}

class Customer implements Runnable{
    private Cinema cinema;
    private String name;
    private List<Integer>buyTickets = new ArrayList<>();

    @Override
    public void run() {
        synchronized (this.cinema){
            List<Integer> copyList = new ArrayList<>(this.cinema.getSetList());
            copyList.retainAll(this.buyTickets);
            if(copyList.size()==this.buyTickets.size()){
                this.cinema.getSetList().removeAll(buyTickets);
                System.out.println(this.name+"购票成功,购买的票是"+this.buyTickets);
                System.out.println("当前可用座位"+this.cinema.getSetList());
            }else {
                System.out.println(this.name+"当前已有座位被购买不好意思");
            }
        }
    }

    public Customer(Cinema cinema, String name, List<Integer> buyTickets) {
        this.cinema = cinema;
        this.name = name;
        this.buyTickets = buyTickets;
    }
}
