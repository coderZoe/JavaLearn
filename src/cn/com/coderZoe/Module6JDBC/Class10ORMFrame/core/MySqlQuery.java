package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core;

import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.ColumnInfo;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.TableInfo;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.po.Employee;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils.JDBCUtils;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils.ReflectUtils;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils.StringUtils;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.vo.EmpVo;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhs
 * @date 2020/5/5 16:22
 * @description 针对mysql数据库的查询
 */
public class MySqlQuery implements Query{
    @Override
    public int executeDML(String sql, Object[] params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBaseManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            JDBCUtils.handleParams(preparedStatement,params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataBaseManager.release(preparedStatement,connection);
        }
        return 0;
    }

    @Override
    public void insert(Object[] params) {
        for(Object object:params){
            Class clazz = object.getClass();
            TableInfo tableInfo = TableContext.poClassTableInfo.get(clazz);
            StringBuilder sql = new StringBuilder();
            List<Object> fieldValues = new ArrayList<>();
            sql.append("insert into "+tableInfo.getTableName()+" (");
            Field[] fields = clazz.getDeclaredFields();
            for(Field field:fields){
                String fieldName = field.getName();
                Object value = ReflectUtils.invokeGet(fieldName,object);
                if(value!=null){
                    sql.append(fieldName+",");
                    fieldValues.add(value);
                }
            }
            //将最后一个逗号换为)
            sql.setCharAt(sql.length()-1,')');
            sql.append(" values (");
            for(int i = 0; i < fieldValues.size(); i++){
                sql.append("?,");
            }
            sql.setCharAt(sql.length()-1,')');
            executeDML(sql.toString(),fieldValues.toArray());
        }
    }

    @Override
    public int delete(Class clazz, Object id) {
        //通过class对象查找表名
        //通过TableContext的poClassTableInfo这个map匹配
        TableInfo tableInfo = TableContext.poClassTableInfo.get(clazz);
        ColumnInfo columnInfo = tableInfo.getPrimaryKey();
        String sql = "delete from "+tableInfo.getTableName()+" where "+columnInfo.getName()+"=?";
        return executeDML(sql,new Object[]{id});
    }

    @Override
    public void delete(Object object) {
        Class clazz = object.getClass();
        TableInfo tableInfo = TableContext.poClassTableInfo.get(clazz);
        ColumnInfo columnInfo = tableInfo.getPrimaryKey();
        Object keyValue = ReflectUtils.invokeGet(columnInfo.getName(),object);
        delete(clazz,keyValue);
    }

    @Override
    public int update(Object object, String[] fields) {
        Class clazz = object.getClass();
        TableInfo tableInfo = TableContext.poClassTableInfo.get(clazz);
        ColumnInfo columnInfo = tableInfo.getPrimaryKey();
        List<Object> fieldValues = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("update "+tableInfo.getTableName()+" set ");
        for(String field:fields){
            Object value = ReflectUtils.invokeGet(field,object);
            fieldValues.add(value);
            sql.append(field+"="+"?,");
        }
        sql.setCharAt(sql.length()-1,' ');
        sql.append("where "+columnInfo.getName()+"=?");
        fieldValues.add(ReflectUtils.invokeGet(columnInfo.getName(),object));
        return executeDML(sql.toString(),fieldValues.toArray());
    }

    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List list = new ArrayList<>();
        try {
            connection = DataBaseManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            JDBCUtils.handleParams(preparedStatement,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            //处理多条
            while (resultSet.next()){
                Object object = clazz.newInstance();
                //处理每一行的多列
                for(int i = 0; i < metaData.getColumnCount();i++){
                    String fieldName = metaData.getColumnLabel(i+1);
                    Object fieldValue = resultSet.getObject(i+1);
                    ReflectUtils.invokeSet(fieldName,fieldValue,object);
                }
                list.add(object);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            DataBaseManager.release(preparedStatement,connection);
        }
        return list;
    }

    @Override
    public Object queryValue(String sql, Object[] params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Object object = null;
        try {
            connection = DataBaseManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            JDBCUtils.handleParams(preparedStatement,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                object = resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseManager.release(preparedStatement,connection);
        }
        return object;
    }

    @Override
    public Number queryNumber(String sql, Object[] params) {
        return (Number)(queryValue(sql,params));
    }

    public static void main(String[] args) {

        //连表查询
        String sql = "select e.id,e.name,e.salary,e.age,e.hiredate,d.depname,d.address from employee e join department d on e.depid=d.id";
        List<EmpVo> empVoList = new MySqlQuery().queryRows(sql,EmpVo.class,null);
        for(EmpVo empVo:empVoList){
            System.out.println(empVo);
        }

        //
        String sql2 = "select count(*) from employee where salary>2000";
        System.out.println(new MySqlQuery().queryNumber(sql2,null));
    }
}
