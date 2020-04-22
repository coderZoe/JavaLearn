package cn.com.coderZoe.IO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author yhs
 * @date 2020/4/8 19:29
 * @description
 */
public class Class21CommonsIOCopyFile {
    /*
    * CommonsIO处理文件拷贝
     */

    public static void main(String[] args) {
        try {
            //1.文件拷贝
            //输入srcFile和dstFile实现从srcFile到dstFile的拷贝
            FileUtils.copyFile(new File("idea.exe"),new File("ideacopy.exe"));

            //2.拷贝文件到目录
            //输入srcFile和dstDirectory，将源文件拷贝到目的目录下，文件名保持一致
            FileUtils.copyFileToDirectory(new File("西电.jpeg"),new File("D:"));

            //3.将一个文件夹所有内容拷贝到到另一个文件夹下(没有目的文件夹将会新建)
            FileUtils.copyDirectoryToDirectory(new File("E:/JavaLearn"),new File("E:/JavaLearnCopy"));

            //4.将一个文件夹下所有的内容拷贝到另一个文件夹(不同于上一个，这里源文件夹的起始目录不会拷贝，会把其实目录内的东西拷贝过去)
            //同样目的文件夹没有会自动新建
            FileUtils.copyDirectory(new File("E:/JavaLearn"),new File("E:/JavaLearnCopy")); //与3的不同就是JavaLearnCopy少了一层JavaLearn文件夹

            //5.拷贝URL内容到文件
            String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586356768236&di=a3389bc8be8618eb72e03b4d02d50659&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fbaike%2Fs%253D600%253Bq%253D50%2Fsign%3D47e6f99e8c0a19d8cf03870503c1f3b6%2Fa8ec8a13632762d07c5a0490acec08fa513dc6bd.jpg";
            FileUtils.copyURLToFile(new URL(url),new File("URLPicture.jpg"));

            //6.将URL内容读取到字符串
            String datas = IOUtils.toString(new URL("http://www.163.com"),"gbk");
            System.out.println(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
