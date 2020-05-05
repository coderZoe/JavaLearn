package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core;

/**
 * @author yhs
 * @date 2020/5/5 14:51
 * @description MySQL类型转化器
 */
public class MySqlTypeConvertor implements TypeConvertor{
    @Override
    public String dateBaseToJava(String columnType) {
        if("varchar".equalsIgnoreCase(columnType)){
         return "String";
        }else if("tinyint".equalsIgnoreCase(columnType)||
                "smallint".equalsIgnoreCase(columnType)||
                "int".equalsIgnoreCase(columnType)||
                "integer".equalsIgnoreCase(columnType)){
            return "Integer";
        }else if("bigint".equalsIgnoreCase(columnType)){
            return "Long";
        }else if("double".equalsIgnoreCase(columnType)){
            return "Double";
        }else if("float".equalsIgnoreCase(columnType)){
            return "Float";
        }else if("Date".equalsIgnoreCase(columnType)){
            return "java.sql.Date";
        }else if("clob".equalsIgnoreCase(columnType)){
            return "java.sql.Clob";
        }else if("blob".equalsIgnoreCase(columnType)){
            return "java.sql.Blob";
        }else if("timestamp".equalsIgnoreCase(columnType)){
            return "java.sql.Timestamp";
        }else {
            return null;
        }
    }

    @Override
    public String javaToDateBase(String javaType) {
        return null;
    }
}
