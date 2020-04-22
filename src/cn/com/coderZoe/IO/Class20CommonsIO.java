package cn.com.coderZoe.IO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yhs
 * @date 2020/4/7 21:18
 * @description
 */
public class Class20CommonsIO {
    /*
    * 笔记
    * CommonsIO是apache公司的开源代码
    * 其主要作用是封装了一些IO操作代码 可以直接拿来用 不再需要自己写
    * 导入CommonsIO的jar包
     */

    public static void main(String[] args) throws IOException {
        //使用commonsIO

        //1.文件大小和目录大小
        long len = FileUtils.sizeOf(new File("H:/Edge下载"));
        System.out.println(len);

        //2.列出子孙集 并给出筛选条件 进行文件过滤
        //listFiles()方法有很多参数  第一个参数是一个File对象，一般是文件夹 第二个参数是对于文件的筛选，比如只要什么后缀的（new SuffixFileFilter("java")），或者文件不能为空的(EmptyFileFilter.NOT_EMPTY)文件
        //还可以针对多个文件类型进行筛选 只需要FileUtils.or(new SuffixFileFilter("java"),new SuffixFileFilter("txt"))
        //当然 有or就有and 比如想筛选这个目录下非空的java文件 FileUtils.and(new SuffixFileFilter("java"),EmptyFileFilter.NOT_EMPTY)
        //第三个参数是文件夹筛选 null是说只要该目录下的一层 DirectoryFileFilter.INSTANCE是说要这个文件夹下的所有层文件信息
        Collection<File> files= FileUtils.listFiles(new File("H:/Edge下载"), new SuffixFileFilter("jar"), DirectoryFileFilter.INSTANCE);
        System.out.println(files);

        //3.读取内容
        //将文件内容直接读取到字符串 (换行符也保留了读取)
        String msg = FileUtils.readFileToString(new File("abc.txt"),"UTF-8");
        System.out.println(msg);
        //读到字节数组
        byte[] bytes = FileUtils.readFileToByteArray(new File("abc.txt"));
        System.out.println(bytes.length);

        //逐行读取 返回给String集合
        List<String> stringList = FileUtils.readLines(new File("abc.txt"),"UTF-8");
        System.out.println(stringList);

        //4.写出内容
        //直接写到文件
        FileUtils.write(new File("target.txt"),"CommonsIO学习","UTF-8");
        FileUtils.write(new File("target.txt"),"CommonsIO学习","UTF-8",true);

        //写出列表
        List<String> strings = new ArrayList<>();
        strings.add("CommonsIO");
        strings.add("学习");
        FileUtils.writeLines(new File("target.txt"),strings,"分隔符",true);
    }
}
