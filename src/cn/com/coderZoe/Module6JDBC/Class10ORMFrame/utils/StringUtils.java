package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils;

/**
 * @author yhs
 * @date 2020/5/5 11:44
 * @description 封装常用字符串操作
 */
public class StringUtils {

    /**
     * @param string 字符串
     * @data: 2020/05/05 15:18
     * @author: yhs
     * @return: {@link String }
     * @description: 将字符串首字母变大写
     */
    public static String firstCharToUppercase(String string){
        String first = string.substring(0,1).toUpperCase();
        return first+string.substring(1);
    }
}
