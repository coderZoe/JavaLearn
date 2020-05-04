package cn.com.coderZoe.Module6JDBC;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;

/**
 * @author yhs
 * @date 2020/5/4 20:20
 * @description 数据库大数据对象的操作
 */
public class Class5CLOBAndBLOB {
    /**
     * CLOB(Character Large Object)字段用于存储大的文本对象
     * mysql中的类型:tinytext text mediumtext longtext
     * BLOB(Binary Large Object)字段用于存储大的二进制流对象
     * mysql中数据类型:tinyblob blob mediumblob longblob
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false","root","root");
        //执行初始化
        String init = "delete from timetest";
        PreparedStatement preparedStatement = connection.prepareStatement(init);
        preparedStatement.executeUpdate();

        //测试大文本对象的使用
        String sql1 = "insert into timetest (id,clob) values(?,?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setInt(1,1);
        //clob需要一个字符流作为参数
        preparedStatement1.setClob(2,new FileReader(new File("163.txt")));
        preparedStatement1.executeUpdate();

        //取出
        String sql2 = "select * from timetest where timetest.id=1";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        ResultSet resultSet = preparedStatement2.executeQuery();
        while (resultSet.next()){
            Clob clob = resultSet.getClob("clob");
            BufferedReader reader = new BufferedReader(clob.getCharacterStream());
            String line = null;
            while ((line = reader.readLine())!=null){
                System.out.println(line);
            }
        }

        //测试BLOB 大二进制对象的使用
        String sql3 = "insert into timetest (id,img) values(?,?)";
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        preparedStatement3.setInt(1,2);
        preparedStatement3.setBlob(2,new FileInputStream(new File("西电.jpeg")));
        preparedStatement3.executeUpdate();

        //取出
        String sql4 = "select * from timetest where timetest.id=2";
        PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
        ResultSet resultSet1 = preparedStatement4.executeQuery();
        while (resultSet1.next()){
            Blob blob = resultSet1.getBlob("img");
            InputStream inputStream = blob.getBinaryStream();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("jdbc西电.jpeg"));
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        }
    }
}
