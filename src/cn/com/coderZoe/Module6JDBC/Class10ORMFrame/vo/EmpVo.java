package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.vo;

import java.sql.Date;

/**
 * @author yhs
 * @date 2020/5/5 21:03
 * @description
 */
public class EmpVo {
    private Integer id;
    private String name;
    private Double salary;
    private Integer age;
    private Date hiredate;
    private String address;
    private String depname;

    public EmpVo() {
    }

    public EmpVo(Integer id, String name, Double salary, Integer age, Date hiredate, String address, String depname) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.hiredate = hiredate;
        this.address = address;
        this.depname = depname;
    }

    @Override
    public String toString() {
        return "EmpVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", hiredate=" + hiredate +
                ", address='" + address + '\'' +
                ", depname='" + depname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }
}
