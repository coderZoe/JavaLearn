package cn.com.coderZoe.IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/5 17:13
 * @description
 */
public class Class19RandomAccessFile {
    /*
    * 笔记
    * RandomAccessFile 该类的实例支持读取和写入随机访问文件
    * 构造方法：RandomAccessFile(File file, String mode) mode参数指定要打开文件的访问模式 r rw rws rwd
    * 操作方式与IO流相同，基本都是read和write方法
    * 除此以外 RandomAccessFile最常用的方法是seek(long pos) 设置文件指针偏移，从该文件的开头测量，发生下一次读取或写入
    * sequenceInputStream 将其他输入流进行逻辑级联 查API即可 核心是将多个输入流放到一个Vector集合里，然后构造方法传入这个集合，进行读写操作
     */

    public static void main(String[] args) {
        //指定起始位置 读取从起始开始你到结尾的所有内容
        int begin = 2;
//        Class19RandomAccessFile.seekRead(begin);

        System.out.println("分隔符-------------------------");

        //读取文本的某一块内容
        //参数 第一个参数是随机读取的开始位置，第二个参数是所需读取的字节大小
        //其实这里的实用性很强，有了线程，每个线程都单独读取一部分信息，然后最后再将信息整合
        int beginPos = 2;
        int fileSize  = 26;
//        Class19RandomAccessFile.readPart(beginPos,fileSize);

        //这里根据上一个函数 可以做实际的多线程下载操作，这里以拷贝一个idea(689M)为例，写个demo
        //没成功 应该是线程的问题 回来再看
        File file = new File("idea.exe");
        long length = file.length();
        int everyPackgeLength = 102400000;  //100M
        int block = (int) (length/everyPackgeLength)+1;

        for(int i = 0; i < block; i++){
            final int j = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    readPart(j*everyPackgeLength,(int)(j == block-1?(length-everyPackgeLength*block):everyPackgeLength));
                }
            });

            thread.start();
        }


    }

    public static void seekRead(int begin){
        //RandomAccessFile 随机读取和写入流
        RandomAccessFile randomAccessFile = null;
        try {
            //构造一个类
            randomAccessFile = new RandomAccessFile(new File("abc.txt"),"r"); //只读
            //指针偏移，从2的位置开始读取（或写入）数据
            randomAccessFile.seek(begin);
            byte[] buffered = new byte[8];
            int len = -1;
            while((len = randomAccessFile.read(buffered))!=-1){
                System.out.println(new String(buffered,0,buffered.length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readPart(int beginPos,int fileSize){
        RandomAccessFile randomAccessFile = null;
        FileOutputStream fileOutputStream = null;
        try {
            randomAccessFile = new RandomAccessFile(new File("abc.txt"),"r");
            fileOutputStream = new FileOutputStream(new File("ideaCopy.exe"),true);
            randomAccessFile.seek(beginPos);
            byte []buffeded = new byte[1024*8];
            int len = -1;
            while((len = randomAccessFile.read(buffeded))!=-1){
                //如果读取的数据还不够，全部读取
                if(fileSize > len){
                    fileOutputStream.write(buffeded,0,buffeded.length);
                    fileSize = fileSize - len;
                }else {     //否则差多少读多少需要的，然后退出循环读取
                    fileOutputStream.write(buffeded,0,buffeded.length);
                    break;
                }
            }
            fileOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
