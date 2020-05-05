package cn.com.coderZoe.Module6JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhs
 * @date 2020/5/4 21:52
 * @description ORM
 */
public class Class7ORMObj {
    /**
     * 笔记
     * ORM object relation mapping
     * 将Java对象与数据库表进行映射 表结构跟类结构对应 表中字段与类中属性对应 表中记录和类对应
     *
     * 测试使用Object数组来封装一条记录
     */

    public static void main(String[] args) {
        try {
            Connection connection = Class6JDBCUtil.getConnection();
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            List<Object[]> objectsList = new ArrayList<>();  //一个Object数组封装一条信息
            while (resultSet.next()){
//                System.out.println(resultSet.getString("name"));
                Object[] objects = new Object[6];
                objects[0]  =resultSet.getObject(1);
                objects[1]  =resultSet.getObject(2);
                objects[2]  =resultSet.getObject(3);
                objects[3]  =resultSet.getObject(4);
                objects[4]  =resultSet.getObject(5);
                objects[5]  =resultSet.getObject(6);
                objectsList.add(objects);
            }

            //遍历输出
            for(Object[] objects:objectsList){
                System.out.println(objects[0]+"--"+objects[1]+"--"+objects[2]+"--"+objects[3]
                        +"--"+objects[4]+"--"+objects[5]);
            }
            Class6JDBCUtil.release(resultSet,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
