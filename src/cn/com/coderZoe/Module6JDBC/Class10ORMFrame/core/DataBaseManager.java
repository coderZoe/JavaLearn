package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core;

import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author yhs
 * @date 2020/5/5 11:42
 * @description 根据配置信息 维持连接对象的管理(增加连接处)
 */
public class DataBaseManager {
    private static Configuration configuration;
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("database.properties")));
            configuration = new Configuration(properties.getProperty("driver"),
                    properties.getProperty("url"),properties.getProperty("username"),
                    properties.getProperty("password"),properties.getProperty("database"),
                    properties.getProperty("srcPath"),properties.getProperty("poPackage"));

            //加载驱动
            Class.forName(configuration.getDriver());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(configuration.getUrl(),configuration.getUsername(),configuration.getPassword());
    }

    public static void release(Statement statement, Connection connection){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection){

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
