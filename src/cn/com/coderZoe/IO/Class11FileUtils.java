package cn.com.coderZoe.IO;
import java.io.*;

/**
 * @author yhs
 * @date 2020/4/2 15:48
 * @description
 */
public class Class11FileUtils {
   /*
   * 笔记
   * 封装拷贝
   * 封装释放
    */

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("西电.jpeg"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("西电copy.jpeg"));
            Class11FileUtils.copy(fileInputStream,fileOutputStream);
            Class11FileUtils.close(fileInputStream,fileOutputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //流到流的传递
    public static void copy(InputStream in, OutputStream out){
        try {
            byte buffer[] = new byte[1024];
            int len = -1;
            while((len = in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //释放资源
    //采用了可变参数 传参不再局限于IOStream，而是扩展到了Closeable接口
    public static void close(Closeable ... ioStream){
        try {
           for(Closeable io:ioStream){
               if(io!=null){
                   io.close();
               }
           }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    * jdk1.7版本释放资源 try...with...resource
    * try(流)会自动释放资源 不再需要finally上例的copy可以改为
    * 这里还是挺奇怪的，try with resource应该是jdk1.7的版本，我这里选择到了jdk1.8但不支持，选了jdk11才支持
    * 应该是idea的问题
     */
//    public static void copy2(InputStream in, OutputStream out){
//        try(in;out){  //这里不需要finally释放资源 会自动释放
//            byte buffer[] = new byte[1024];
//            int len = -1;
//            while((len = in.read(buffer))!=-1){
//                out.write(buffer,0,len);
//            }
//            out.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}
