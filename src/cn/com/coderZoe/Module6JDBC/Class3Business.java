package cn.com.coderZoe.Module6JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author yhs
 * @date 2020/5/4 18:51
 * @description 事务
 */
public class Class3Business {

    /**
     * 笔记
     * 事务:
     * 一组要么同时执行成功 要么同时执行失败的SQL语句 是数据库操作的一个执行单元
     *
     * 事务开始于:
     * 1.连接数据库并执行一条DML语句(增删改)
     * 2.前一个事务结束后 又执行另一个DML语句
     *
     * 事务结束于:
     * 1.执行commit或rollback语句
     * 2.执行一条DDL语句(建表) 这种情况下会自动执行commit语句
     * 3.执行一条DCL语句(权限操作 例如Grant语句) 这种情况下会自动执行commit语句
     * 4.断开与数据库的连接 这种情况下会自动执行commit语句
     * 5.执行DML语句过程中失败 事务回滚 事务结束
     *
     * 事务的四大特性(ACID):
     * 1.atomicity(原子性):一个事务内的所有操作是一个整体 要么全部成功 要么全部失败
     * 2.consistency(一致性):一个事务内有一个操作失败时 所有更改过的数据都必须回滚到更改前的状态
     * 3.isolation(隔离性):事务查看数据时数据所处的状态,要么是另一并发事务修改它之前的状态要么是另一事务修改它之后的状态 事务不会查看中间状态
     * 4，durability(持久性): 持久性事务完成之后，他对于系统的影响是永久性的
     *
     * 事务隔离级别从低到高：
     * 读取未提交
     * 读取已提交(默认)
     * 可重复读
     * 序列化
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    }
}
