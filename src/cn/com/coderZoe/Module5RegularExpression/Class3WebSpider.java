package cn.com.coderZoe.Module5RegularExpression;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yhs
 * @date 2020/5/2 22:35
 * @description 手写网络爬虫 正则解析数据
 */
public class Class3WebSpider {

    /**
     *
     */
    public static void main(String[] args) throws IOException {
        File file = new File("163.txt");
        FileUtils.copyURLToFile(new URL("https://www.163.com/"),file);
        String content = FileUtils.readFileToString(file, "gbk");
//        System.out.println(content);

        //匹配正则
        Pattern pattern = Pattern.compile("<a.+>.+</a>");
        Pattern pattern1 = Pattern.compile("href=\"(http://.+)\"");
        Matcher matcher = pattern.matcher(content);
        Matcher matcher1 = pattern1.matcher(content);
//        while (matcher.find()){
//            System.out.println(matcher.group());
//        }
        while (matcher1.find()){
            System.out.println(matcher1.group(1));
        }


    }
}
