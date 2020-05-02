package cn.com.coderZoe.Module5RegularExpression;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yhs
 * @date 2020/5/2 21:48
 * @description 开发环境中使用正则表达式
 */
public class Class2RegularTest {
    /**
     * Java中使用正则表达式
     * Pattern 正则表达式的编译形式 Pattern pattern = Pattern.compile(expression)
     * Matcher 通过解释Pattern对字符串进行匹配操作 Matcher matcher = pattern.matcher(string);
     */
    public static void main(String[] args) {
        String string = "dsakudgkuashdu2345d76sa6d968af";
        Pattern pattern = Pattern.compile("\\w+");  //正则里的一个\变成java里的两个\\
        Matcher matcher = pattern.matcher(string);
        boolean is = matcher.matches();     //尝试将整个字符序列与该模式匹配
        boolean is2 = matcher.find();   //扫描输入的序列 查找与该模式匹配的一个子序列 每次调用都会查找下一个子序列

        Pattern pattern1 = Pattern.compile("\\d+");
        Matcher matcher1 = pattern1.matcher(string);
        while (matcher1.find()){
            System.out.println(matcher1.group());
        }

        //group代表捕获组 可以传参 举例:
        String string1 = "fsdhkj34*******hkjlds40********ds654)(vdjcfl54089897";
        Pattern pattern2 = Pattern.compile("([a-z]+)([0-9]+)");
        Matcher matcher2 = pattern2.matcher(string1);
        while (matcher2.find()){
            System.out.println(matcher2.group());
            System.out.println(matcher2.group(1));  //[a-z]+中的内容
            System.out.println(matcher2.group(2));  //[0-9]+中的内容
        }

        //替换操作
        String string2 = "fsdhkj34*******hkjlds40********ds654)(vdjcfl54089897";
        Pattern pattern3 = Pattern.compile("\\d+");
        Matcher matcher3 = pattern3.matcher(string2);
        System.out.println(matcher3.replaceAll("数字"));  //将\d替换为数字

        //分割
        String string3 = "dsakudgkuashdu2345d76sa6d968af";
        String string4 = "aav_sda_dad_sdada_";
        System.out.println(Arrays.toString(string4.split("_")));    //字符串的简单分割
        System.out.println(Arrays.toString(string3.split("\\d+")));  //支持传入正则表达式 按数字进行切割
    }
}
