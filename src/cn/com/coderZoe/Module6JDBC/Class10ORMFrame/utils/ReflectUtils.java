package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yhs
 * @date 2020/5/5 11:45
 * @description 封装常用反射操作
 */
public class ReflectUtils {
    /**
     * @param fieldName 字段名
     * @param object    对象
     * @data: 2020/05/05 17:06
     * @author: yhs
     * @return: {@link Object }
     * @description: 调用po的get方法并返回
     */
    public static Object invokeGet(String fieldName,Object object){
        Class clazz = object.getClass();
        try {
            Method method = clazz.getDeclaredMethod("get"+StringUtils.firstCharToUppercase(fieldName));
            return method.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void invokeSet(String fieldName,Object fieldValue,Object object){
        Class clazz = object.getClass();
        try {
            Method method = clazz.getDeclaredMethod("set"+StringUtils.firstCharToUppercase(fieldName),fieldValue.getClass());
            method.invoke(object,fieldValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
