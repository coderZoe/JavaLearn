package cn.com.coderZoe.Module6JDBC;

import java.sql.*;
import java.util.Random;

/**
 * @author yhs
 * @date 2020/5/4 21:31
 * @description
 */
public class Class6JDBCPackageTest {
    public static void main(String[] args) {
        try {
            Connection connection = Class6JDBCUtil.getConnection();
            String sql = "insert into timetest (id,date) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 3; i < 100; i++){
                int rand = 1000000000+new Random().nextInt(1000000000);
                preparedStatement.setInt(1,i);
                preparedStatement.setDate(2,new Date(System.currentTimeMillis()-rand));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            Class6JDBCUtil.release(preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
