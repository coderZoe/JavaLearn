package cn.com.coderZoe.IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/2 14:20
 * @description
 */
public class Class9ByteArrayStream {
    /*
    * 之前讲的字节流方法或字符流方法 都是对文件进行操作的，比如FileInputStream FileReader 都是对硬盘上的文件读取
    * 除了数据源是硬盘文件外 还可以是内存，即字节数组，不再是文件 而是内存
    * 与文件不同 Java访问硬盘的文件 需要和操作系统打交道，通知操作系统读写或关闭读写，但内存不用，Java可以直接操作字节 ，所以字节数组流不需要关闭
    * 字节数组流 不需要关闭IO流
    * ByteArrayOutputStream字节数组流构造函数不需要参数 因为Java自己定了一个缓冲区域 用于写数据
     */

    public static void main(String[] args) {
        //ByteArrayInputStream
        //step1.字节数组文件源
        byte[] src = "talk is cheap show me the code".getBytes();   //ByteArrayStream需要byte数组作为输入源
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(src);
            byte[] buffer = new byte[5];
            int len = -1;
            while ((len = in.read(buffer))!=-1){
                System.out.println(new String(buffer));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //不再需要finally了，因为ByteArrayStream读取的是内存，内存的销毁是由GC自动处理，无需人为干涉


        //ByteArrayOutputStream
        try {
            //byteArrayOutputStream构造方法不需要参数 也即不需要一个流的目的地
            //Java内部会自动设置一个缓冲区，当写入数据时 缓冲区会自动增长，可以使用toByteArray()或toStrting()检索数据
            //这里想使用ByteArrayOutputStream自己的方法(toByteArray())，所以不能再像以前一样使用多态。

            //将字节数组写到内存中
            byte [] src1 = "talk is cheap show me the code".getBytes();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(src1);
            System.out.println(out.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
