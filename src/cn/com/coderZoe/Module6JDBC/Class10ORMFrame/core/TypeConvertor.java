package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core;

/**
 * @author yhs
 * @date 2020/5/5 11:36
 * @description JavaBean与SQL转化接口
 */
public interface TypeConvertor {

    /**
     * @param columnType 列类型
     * @data: 2020/05/05 11:38
     * @author: yhs
     * @return: {@link String } 返回Java数据类型
     * @description: 将数据库数据类型转化为Java类型
     */
    String dateBaseToJava(String columnType);

    /**
     * @param javaType java类型
     * @data: 2020/05/05 11:39
     * @author: yhs
     * @return: {@link String } 返回数据库数据类型
     * @description: Java数据类型转数据库类型
     */
    String javaToDateBase(String javaType);
}
