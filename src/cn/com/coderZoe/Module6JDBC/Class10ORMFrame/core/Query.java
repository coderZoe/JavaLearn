package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core;

import java.util.List;

/**
 * @author yhs
 * @date 2020/5/5 11:12
 * @description 查询接口 负责查询 不同的数据库自己具体去实现
 */
public interface Query {

    /**
     * @param sql    sql
     * @param params 参数
     * @data: 2020/05/05 11:15
     * @author: yhs
     * @return: int
     * @description: 执行SQL语句后影响了几行记录
     */
    int executeDML(String sql, Object[] params);

    /**
     * @param params 参数
     * @data: 2020/05/05 11:18
     * @author: yhs
     * @return:
     * @description: 将一个对象存储到数据库中
     */
    void insert(Object[] params);

    /**
     * @param clazz clazz 跟表对应的表中的结构
     * @param id    id
     * @data: 2020/05/05 11:20
     * @author: yhs
     * @return: int
     * @description: 根据id删除
     */
    int delete(Class clazz,Object id);

    /**
     * @param object 对象
     * @data: 2020/05/05 11:21
     * @author: yhs
     * @return:
     * @description: 删除单个对象
     */
    void delete(Object object);

    /**
     * @param object 对象
     * @param field  更新的对象域
     * @data: 2020/05/05 11:23
     * @author: yhs
     * @return: int
     * @description: 更新对象对应的记录 只更新指定字段的属性
     */
    int update(Object object,String[] field);


    /**
     * @param sql    sql
     * @param clazz  封装数据的JavaBean结构
     * @param params 参数
     * @data: 2020/05/05 11:26
     * @author: yhs
     * @return: {@link List } 返回查询的结果
     * @description: 返回多行记录 并将每行记录封装到clazz指定的对象中
     */
    List queryRows(String sql,Class clazz,Object[] params);

    /**
     * @param sql    sql
     * @param params 参数
     * @data: 2020/05/05 11:31
     * @author: yhs
     * @return: {@link Object }
     * @description: 查询一条结果的某个值
     */
    Object queryValue(String sql,Object[] params);

    /**
     * @param sql    sql
     * @param params 参数
     * @data: 2020/05/05 11:33
     * @author: yhs
     * @return: {@link Number }
     * @description: 返回一个数字 select count这种
     */
    Number queryNumber(String sql,Object[] params);
}
