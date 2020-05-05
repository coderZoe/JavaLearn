package cn.com.coderZoe.Module6JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/5/4 22:34
 * @description 通过JavaBean封装
 */
public class Class9ORMJavaBean {
    public static void main(String[] args) {
        try {
            Connection connection = Class6JDBCUtil.getConnection();
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()){
                Employee employee = new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6));
                employeeList.add(employee);
            }

            for(Employee employee:employeeList){
                System.out.println(employee);
            }
            Class6JDBCUtil.release(resultSet,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/**
 * @data: 2020/05/04 22:37
 * @author: yhs
 * @description: 数据库表
 */
class Employee{
    private int id;
    private String name;
    private double salary;
    private int age;
    private Date hiredate;
    private int depid;

    public Employee() {
    }

    public Employee(int id, String name, double salary, int age, Date hiredate, int depid) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.hiredate = hiredate;
        this.depid = depid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", hiredate=" + hiredate +
                ", depid=" + depid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }
}
