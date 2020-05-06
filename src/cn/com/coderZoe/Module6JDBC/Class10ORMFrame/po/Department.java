package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.po;

import java.sql.*;
import java.util.*;

public class Department {

	private String address;
	private Integer id;
	private String depname;

	public String getAddress() {
		return this.address;
	}
	public Integer getId() {
		return this.id;
	}
	public String getDepname() {
		return this.depname;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}

}
