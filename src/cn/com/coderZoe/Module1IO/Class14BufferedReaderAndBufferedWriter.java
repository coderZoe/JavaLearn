package cn.com.coderZoe.Module1IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/2 20:34
 * @description
 */
public class Class14BufferedReaderAndBufferedWriter {
    /*
    * 笔记
    * 字符缓冲流 与字节缓冲流效果一样
    * 字符缓冲流自己有很多不同于Reader和Writer的新方法
    * Bufferedeader类新方法:
    * readLine() 自动找换行符 一行行读
    * BufferedWriter类新方法
    * newLine() 写一个换行符
     */

    public static void main(String[] args) {
        //BufferedReader BufferedWriter

        //确定文件源
        File srcFile = new File("abc.txt");
        File targetFile = new File("target.txt");

        //选则流
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(srcFile));
            writer = new BufferedWriter(new FileWriter(targetFile));

            String line = null;

            //新方法 BufferedReader方法 reaadLine()自动寻找一行来读取
            while((line = reader.readLine()) != null){

                //这里readLine()方法并不会把换行符读进来，想换行自己加
                writer.write(line);
                //新方法 BufferedWriter方法 newLine()方法会自动换行（输出一个换行符）==writer.write("\n")
                writer.newLine();
            }
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
