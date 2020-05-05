package cn.com.coderZoe.Module6JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author yhs
 * @date 2020/5/4 18:33
 * @description JDBC批处理
 */
public class Class2Batch {
    /**
     * 笔记
     * JDBC批处理
     * 对于批处理建议用Statement 因为PreparedStatement预编译空间有限 当数据量特别大时会发生异常
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false","root","root");

        //开启事务
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        for(int i = 0; i < 2000; i++){
            String sql = "insert into jdbctest(name,age,description) values ('yhs',"+i+",'coder')";
            statement.addBatch(sql);
        }
        //执行
        statement.executeBatch();
        statement.clearBatch();
        //提交事务
        connection.commit();
    }
}
