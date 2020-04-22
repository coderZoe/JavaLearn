package cn.com.coderZoe.IO;

import java.io.*;
import java.util.Arrays;

/**
 * @author yhs
 * @date 2020/4/2 15:06
 * @description
 */
public class Class10FileStreamAndByteArrayStream {
    /*
    * 流对接 之前都是单一流的处理 比如文件流读 文件流写 字节流读 字节流写
    * 现在的任务是 读取文件数据 转化为字节数组流输出 读取字节数组流转化为文件流输出
     */

    public static void main(String[] args) {
        //将文件流转为字节数组流
        File file = new File("西电.jpeg");
        byte src[] = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = -1;
            while((len=fileInputStream.read(buff))!=-1){
                byteArrayOutputStream.write(buff,0,len);
            }
            byteArrayOutputStream.flush();
            src = byteArrayOutputStream.toByteArray();
            //输出字节数组
            System.out.println(Arrays.toString(src));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fileInputStream!=null){
                    fileInputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //将字节数组转化为文件
        FileOutputStream fileOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        File targetFile = new File("targetPicture.jpeg");
        try {
            //将src作为数据源
            byteArrayInputStream = new ByteArrayInputStream(src);
            fileOutputStream = new FileOutputStream(targetFile);
            byte[] buff = new byte[1024];
            int len = -1;
            while((len=byteArrayInputStream.read(buff))!=-1){
                fileOutputStream.write(buff,0,len);
            }
            fileOutputStream.flush();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
