package cn.com.coderZoe.Module6JDBC;

import java.sql.*;
import java.util.Calendar;
import java.util.Random;

/**
 * @author yhs
 * @date 2020/5/4 19:32
 * @description 数据库时间类型
 */
public class Class4DateTime {
    /**
     * Java.sql.Date 年月日
     * Java.sql.Time 时分秒
     * Java.sql.Timestamp 年月日时分秒
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false","root","root");
        //执行初始化
        String init = "delete from timetest";
        PreparedStatement preparedStatement0 = connection.prepareStatement(init);
        preparedStatement0.executeUpdate();

        String sql = "insert into timetest(id,date,time) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,1);
        preparedStatement.setDate(2,new Date(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();

        //插入随机日期
        for(int i = 2; i < 1000; i++){
            String sql1 = "insert into timetest(id,date,time) values (?,?,?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            int rand = 1000000000+new Random().nextInt(1000000000);
            preparedStatement1.setInt(1,i);
            preparedStatement1.setDate(2,new Date(System.currentTimeMillis()-rand));
            preparedStatement1.setTimestamp(3,new Timestamp(System.currentTimeMillis()-rand));
            preparedStatement1.executeUpdate();
        }

        //插入之后 按时间范围进行筛选查询
        long startTime = new java.util.Date(2020-1900, Calendar.APRIL,20).getTime();
        System.out.println(startTime);
        long endTime = new java.util.Date(2020-1900, Calendar.MAY, 1).getTime();
        Date start = new Date(startTime);
        Date end = new Date(endTime);
        String sql1 = "select * from timetest where timetest.date between ? and ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setDate(1,start);
        preparedStatement1.setDate(2,end);
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+"------"+resultSet.getDate(2)+"------"+resultSet.getTimestamp(3));
        }
    }
}
