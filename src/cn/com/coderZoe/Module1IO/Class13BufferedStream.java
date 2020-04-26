package cn.com.coderZoe.Module1IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/2 20:01
 * @description
 */

public class Class13BufferedStream {
    /*
    * 笔记
    * BufferedInputStream和BufferedOutputStream是处理流类 翻译为字节缓冲流
    * 有用于字符缓冲的BufferedReader和BufferedWriter
    * 缓冲流作用是提高了读写的性能 加大了一次读写的大小 降低了读写的次数
    * 缓冲流的使用方法是用一个节点流做参数来构造 类似于缓冲流里装了一个节点流 BufferedInputStream(InputStream in)
    * 1.缓冲流或者说处理流提升了性能
    * 2.必须要有一个节点流，节点流是根本
    * 3.释放的时候 只需要释放处理流，无需释放节点流，处理流自己会释放里面的节点流
     */

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Class13BufferedStream.bufferedCopy();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);      //13ms

        long startTime1 = System.currentTimeMillis();
        Class13BufferedStream.streamCopy();
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1-startTime1);    //383ms
    }

    public static void bufferedCopy() throws Exception{
        //BufferdInPutStream和BufferedOutputStream
        File file = new File("西电.jpeg");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        File file1 = new File("西电copy.jpeg");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        int len = -1;
        byte[] buffered = new byte[8];
        while((len = bufferedInputStream.read(buffered))!= -1){
            bufferedOutputStream.write(buffered,0,len);
        }
        bufferedOutputStream.flush();

        bufferedOutputStream.close();
        bufferedInputStream.close();
    }

    public static void streamCopy() throws Exception{
        //BufferdInPutStream和BufferedOutputStream
        File file = new File("西电.jpeg");
        FileInputStream fileInputStream = new FileInputStream(file);
        File file1 = new File("西电copy1.jpeg");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        int len = -1;
        byte[] buffered = new byte[8];
        while((len = fileInputStream.read(buffered))!= -1){
            fileOutputStream.write(buffered,0,len);
        }
        fileOutputStream.flush();

        fileOutputStream.close();
        fileInputStream.close();
    }
}
