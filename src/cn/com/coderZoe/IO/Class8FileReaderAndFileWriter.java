package cn.com.coderZoe.IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/2 14:01
 * @description
 */
public class Class8FileReaderAndFileWriter {
    /*
    * 笔记
    * 字符流 之前处理的都是字节流，字节流的读写都是byte，现在处理字符流
    * FileReader 文件输入字符流 FileWriter 文件输出字符流
    * 字符流处理字符的好处是 不用关心字符的编码方式，直接读 直接写。如果用字节流 ，在写的时候部分字节比如中文可能因编码的不同会出现乱码
   */

    public static void main(String[] args) {
        File file = new File("abc.txt");
        File file1 = new File("target.txt");
        Reader reader = null;
        Writer writer = null;
        try {
            //选择流将流和文件关联
            reader = new FileReader(file);
            writer = new FileWriter(file1);
            char[] buffer = new char[10];
            int len;
            while((len = reader.read(buffer))!=-1){
                writer.write(buffer);
            }
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
