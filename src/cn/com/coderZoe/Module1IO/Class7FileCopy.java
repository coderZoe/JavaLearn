package cn.com.coderZoe.Module1IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/2 11:18
 * @description
 */
public class Class7FileCopy {
    /*
    * 笔记
    * 文件拷贝
    * 文件拷贝其实就是将输入流与输出楼合二为一，接收输入流 对输入流进行处理 再通过输出流写出
     */

    public static void main(String[] args) {
        //使用文件字节输入流和文件字节输出流达到文件的拷贝

        // step 1 创建文件源
        File srcFile = new File("abc.txt");
        File dstFile = new File("target.txt");

        InputStream in = null;
        OutputStream out = null;
        try {
            //step 2 选择流 这里选择 文件字节流
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(dstFile);

            //step3 文件操作 (读写)
            byte[] buffer = new byte[10];
            int len = -1;
            while((len = in.read(buffer))!= -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //step4 释放资源
            //先打开的后关闭（类似于栈 先进后出）
            if(out!=null){
                try {
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }
}
