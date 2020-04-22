package cn.com.coderZoe.IO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author yhs
 * @date 2020/3/31 21:24
 * @description
 */
public class Class2File {
    /*
    *笔记
    * Java中File类用来抽象表述数据文件源 不过File不仅表示文件，还可以表示目录
    * API看法
    * 1.看类的继承体系
    * 2.看类的说明作用
    * 3.看类的数据域和方法
    *
    * FileAPI
    * 1.getName()获得文件名 getPath()构建时相对路径就返回相对路径 绝对路径就返回绝对路径 getAbsolutePath()返回绝对路径 getParent()返回父文件路径 没有就返回空
    * 2.exists() 是否存在 isFile() 是否是文件 isDirectory()是否是目录
    * 3.length()文件字节
    * 4.createNewFile() 创建该文件(不是文件夹) delete() 删除File对象
    * 5.mkdir() mkdirs() 创建文件夹(目录) mkdir如果父目录不存在创建失败 mkdirs无论父目录是否存在 都会创建成功
    * 6.list() 下级的名称 listFiles() 下级File
    * 7.listRoots() 根路径
     */

    public static void main(String[] args) throws IOException {

        //getName() getPath() getAbsolutePath() getParent() getParentFile()
        File file = new File("E:/文件资料/jdk api 1.8.CHM");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println("返回父对象："+file.getParentFile().getName());

        //文件状态函数 exists() isFile() isDirectory()
        File file1 = new File("E:/文件资料/jdk api 1.8.CHM");
        System.out.println(file1.exists());
        System.out.println(file1.isFile());
        System.out.println(file.isDirectory());

        //文件状态的标准代码
        File file2 = new File("E:/文件资料/jdk api 1.8.CHM");
        if(file.exists()){
            if(file2.isFile()){
                //做文件的操作
                System.out.println("File2是文件");
            }else {
                //做目录的操作
                System.out.println("File2是文件夹");
            }
        }else {
            //处理文件不存在的操作
            System.out.println("文件不存在");
        }

        //length()
        File file3 = new File("E:/文件资料/jdk api 1.8.CHM");
        System.out.println(file3.length());
        //文件夹长度为0
        System.out.println(file3.getParentFile().length());

        //createNewFile() delete()
        File file4 = new File("E:/文件资料/瞎写的文件名.txt");
        System.out.println(file4.createNewFile()); //创建成功返回true 失败返回false
        System.out.println(file4.createNewFile());
        System.out.println("createNewFile()不能单独创建文件夹");
        System.out.println(file4.delete()); //删除成功返回true 失败返回flase

        //mkdir() mkdirs()
        File file5 = new File("E:/瞎写的目录/瞎写的文件夹");
        System.out.println(file5.mkdir());
        System.out.println(file5.mkdirs());
        file5.delete();
        file5.getParentFile().delete();

        // list() listFiles()列出下级
        File file6 = new File("E:/");
        String [] subFileName = file6.list();
        System.out.println(Arrays.toString(subFileName));

        File [] files = file6.listFiles();
        for(File temp: files){
            System.out.println(temp.getName());
        }

         //listRoots()
        System.out.println(Arrays.toString(File.listRoots()));
    }
}
