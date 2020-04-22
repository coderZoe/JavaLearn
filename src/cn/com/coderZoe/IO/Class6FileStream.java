package cn.com.coderZoe.IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/1 21:24
 * @description
 */
public class Class6FileStream {
    /*
    * 文件字节流 FileInputStream FileOutputStream
    *
     */

    public static void main(String[] args) {

        //FileInputStream
        File file = new File("abc.txt");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            //做一个缓冲流 不再是一个字节一个字节的读取
            byte[] buffer = new byte[5];
            //此时read的返回结果是读取的字节长度 若读取到文件尾 返回-1
            int len;
            while ((len = in.read(buffer))!= -1){
                System.out.println(new String(buffer,0,len)); //字节转String 直接用String构造方法
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        //FileOutputStream 文件字节输出流 一般用来处理音频图片  字符可以用FileWriter
        //1.创建源
        File file1 = new File("dst.txt");
        OutputStream out = null;
        try {
            String msg = "this is my output words have a see";
            //将输出流与文件连接起来
            out = new FileOutputStream(file1);
//            下面这个构造方法多个true，指的是在File文件追加信息，不加true是覆盖信息
//            out = new FileOutputStream(file1,true);
            byte[] datas = msg.getBytes();  //将字符串转为字节数组 因为OutputStream是字节输出流 只能输出字节
            out.write(datas);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!= null){
                try {
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
