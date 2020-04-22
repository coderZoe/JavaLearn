package cn.com.coderZoe.IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/4 11:42
 * @description
 */
public class Class17ObjectInputStreamAndObjectOutputStream {
    /*
    * 笔记
    * 对象流
    * 类转流叫做序列化 流转类叫做反序列化
    * 对象通过输出流转为文件（或其他）进行存储 同时文件（或其他）通过输入流来读取转为对象
    * ObjectInputStream readObject()
    * ObjectOutputStream writeObject(Object obj)
     */

    public static void main(String[] args) {

        File srcFile = new File("data.txt");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(srcFile)));
            out.writeObject(new Employee("小明",24,9000));
            out.flush();

            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)));
            Employee employee =(Employee) in.readObject();
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
            System.out.println(employee.getSalary());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Employee implements Serializable{
    private String name;
    private int age;
    private double salary;

    //某些数据比较敏感 不需要序列化 加关键字transient，这样在readObject的时候这个数据就隐藏了 显示不出来
    //private transient double salary;

    public Employee() {
    }

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
