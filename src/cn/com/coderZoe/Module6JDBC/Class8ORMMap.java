package cn.com.coderZoe.Module6JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/5/4 22:19
 * @description 测试使用map来封装一条记录
 */
public class Class8ORMMap {
    public static void main(String[] args) {
        try {
            Connection connection = Class6JDBCUtil.getConnection();
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String,Object>> mapList = new ArrayList<>(); //一个map存储一条记录

            while (resultSet.next()){
                Map<String ,Object> objectMap = new HashMap<>();
                objectMap.put("id",resultSet.getObject(1));
                objectMap.put("name",resultSet.getObject(2));
                objectMap.put("salary",resultSet.getObject(3));
                objectMap.put("age",resultSet.getObject(4));
                objectMap.put("hiredate",resultSet.getObject(5));
                objectMap.put("depid",resultSet.getObject(6));
                mapList.add(objectMap);
            }

            //遍历输出
            for(Map<String,Object>objectMap:mapList){
                System.out.println(objectMap.get("id")+"--"+objectMap.get("name")+"--"+objectMap.get("salary")+"--"+
                        objectMap.get("age")+"--"+objectMap.get("hiredate")+"--"+objectMap.get("depid"));
            }
            Class6JDBCUtil.release(resultSet,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
