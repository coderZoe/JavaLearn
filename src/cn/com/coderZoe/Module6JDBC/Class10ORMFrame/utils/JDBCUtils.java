package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yhs
 * @date 2020/5/5 11:44
 * @description 封装常用JDBC操作
 */
public class JDBCUtils {
    public static void handleParams(PreparedStatement preparedStatement,Object[] params) throws SQLException {
        if(params!=null){
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1,params[i]);
            }
        }
    }
}
