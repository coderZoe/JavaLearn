package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core;

import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.ColumnInfo;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.TableInfo;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils.JavaFileUtils;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/5/5 11:41
 * @description 获取管理数据库所有表结构与类结构的关系 并可以根据表结构生成类结构
 */
public class TableContext {

    /**
     *  数据库内的表 表名为key 表信息为value
     */
    private static Map<String,TableInfo> tableInfoMap = new HashMap<>();

    /**
     * 将po的class对象和表信息关联起来
     */
    public static Map<Class, TableInfo> poClassTableInfo = new HashMap<>();

    private TableContext(){}

    /**
     *  将表结构加载进来
     */
    static {
        try {
            Connection connection = DataBaseManager.getConnection();
            //DatabaseMetaData包含了数据库表中所有信息
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            ResultSet resultSet = databaseMetaData.getTables(null,"%","%",new String[]{"TABLE"});
            //遍历 获得所有表信息
            while (resultSet.next()){
                //构造单个表的信息
                String tableName = resultSet.getString("TABLE_NAME");
                TableInfo tableInfo = new TableInfo(tableName,new HashMap<String ,ColumnInfo>(),new ArrayList<ColumnInfo>());
                tableInfoMap.put(tableName,tableInfo);

                //获得当前表的列信息
                ResultSet resultSet1 = databaseMetaData.getColumns(null,"%",tableName,"%");

                //遍历列信息
                while (resultSet1.next()){
                    //字段名 字段数据类型和字段键类型
                    ColumnInfo columnInfo = new ColumnInfo(resultSet1.getString("COLUMN_NAME"),resultSet1.getString("TYPE_NAME"),0);
                    tableInfo.getColumnInfoMap().put(resultSet1.getString("COLUMN_NAME"),columnInfo);
                }
                //获得主键进行遍历
                ResultSet resultSet2 = databaseMetaData.getPrimaryKeys(null,"%",tableName);
                while (resultSet2.next()){
                    //更改列的主键属性 同时将这个列信息加入table的主键字段中
                    ColumnInfo columnInfo = tableInfo.getColumnInfoMap().get(resultSet2.getString("COLUMN_NAME"));
                    columnInfo.setKeyType(1);
                    tableInfo.getPrimaryKeys().add(columnInfo);
                }
                //设置唯一主键
                if(tableInfo.getPrimaryKeys().size()>0){
                    tableInfo.setPrimaryKey(tableInfo.getPrimaryKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateJavaSrc();
        loadPoTables();
    }

    /**
     * @data: 2020/05/05 16:27
     * @author: yhs
     * @return:
     * @description: 从表结构到类的转化
     */
    public static void updateJavaSrc(){
        Map<String,TableInfo> tableInfoMap = TableContext.getTableInfoMap();
        for(TableInfo tableInfo:tableInfoMap.values()){
            JavaFileUtils.writeJavaSrc(tableInfo,new MySqlTypeConvertor());
        }

    }


    /**
     * @data: 2020/05/05 16:27
     * @author: yhs
     * @return:
     * @description: 加载PO包下的类
     */
    public static void loadPoTables(){
        for(TableInfo tableInfo:tableInfoMap.values()){
            try {
                Class clazz = Class.forName(DataBaseManager.getConfiguration().getPoPackage()+ "."+StringUtils.firstCharToUppercase(tableInfo.getTableName()));
                poClassTableInfo.put(clazz,tableInfo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static Map<String, TableInfo> getTableInfoMap() {
        return tableInfoMap;
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
