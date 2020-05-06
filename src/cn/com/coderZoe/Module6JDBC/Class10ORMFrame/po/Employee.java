package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.po;

import java.sql.*;
import java.util.*;

public class Employee {

	private String name;
	private Integer depid;
	private Integer id;
	private Double salary;
	private java.sql.Date hiredate;
	private Integer age;

	public String getName() {
		return this.name;
	}
	public Integer getDepid() {
		return this.depid;
	}
	public Integer getId() {
		return this.id;
	}
	public Double getSalary() {
		return this.salary;
	}
	public java.sql.Date getHiredate() {
		return this.hiredate;
	}
	public Integer getAge() {
		return this.age;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public void setHiredate(java.sql.Date hiredate) {
		this.hiredate = hiredate;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}
