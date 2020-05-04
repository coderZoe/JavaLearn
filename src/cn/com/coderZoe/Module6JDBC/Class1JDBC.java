package cn.com.coderZoe.Module6JDBC;

import java.sql.*;

/**
 * @author yhs
 * @date 2020/5/3 20:20
 * @description
 */
public class Class1JDBC {

    /**
     * Java DataBase Connection Java数据库连接
     * Sun公司提供一组接口 具体的实现由具体的产商来实现 数据库厂商的实现类一般叫做数据库驱动
     *
     * JDBC访问数据库流程:
     * 1.加载驱动
     * 2.连接数据库(Socket连接)
     * 3.SQL语句
     * 4.结果集
     * 5.关闭连接 ResultSet->Statement->Connection
     *
     * Statement接口:
     * 1.Statement:由connection.createStatement创建 用于发送简单的SQL语句(不带参数)
     * 2.PreparedStatement:继承自Statement接口 由connection.preparedStatement创建 用于发送一个或多个输入参数的sql语句
     * PreparedStatement比Statement效率更高 并且可以防止SQL注入
     * 3.CallableStatement:继承自PreparedStatement由connection.prePareCall创建 用于调用存储过程
     *
     * 常用的Statement方法:
     * execute() 运行SQL语句 返回是否有结果集
     * executeQuery() 运行select语句 返回ResultSet结果集
     * executeUpdate() 运行insert/update/delete语句 返回更新的行数
     *
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //step1:加载驱动类
        Class.forName("com.mysql.jdbc.Driver");

        //step2:连接数据库
        //说明:本来的mysql连接URL为:jdbc:mysql://localHost:3306/device 但因为新版本mysql会有warn虽然不影响使用 因为高版本会进行SSL验证 所以加上useSSL=false
        //一般来说connection会建立一个socket连接 会比较耗时
        //实际工作一般会通过连接池来做
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false","root","root");

        //step3:SQL语句
        Statement statement = connection.createStatement();
        String sql = "delete from jdbctest";
        String sql1 = "insert into jdbctest(name,age,description) values ('yhs',25,'coder')";
        //设一条删除 做初始虎啊
        statement.execute(sql);
        statement.execute(sql1);
        //Statement接口容易引起SQL注入 外界可以写SQL语句来作为参数改变SQL的执行

        //实际更多的使用PreparedStatement 实际具有预编译的过程 提高SQL效率和预防SQL注入
        String sql2 = "insert into jdbctest(name,age,description) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setString(1,"Zoe");   //第一个问好
        preparedStatement.setInt(2,25);         //第二个问号
        preparedStatement.setString(3,"coderZoe");  //第三个问号
        preparedStatement.execute();


        //step4:结果集
        String name = "yhs";
        String sql3 = "select * from jdbctest where jdbctest.name like ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql3);
        preparedStatement1.setString(1,name);
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()){
            //取出结果集(一条记录)的1 2 3列信息
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }
        //step5:关闭
        if(resultSet!=null){
            resultSet.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if(preparedStatement1!=null){
            preparedStatement1.close();
        }
        if(connection!=null){
            connection.close();
        }
    }
}
