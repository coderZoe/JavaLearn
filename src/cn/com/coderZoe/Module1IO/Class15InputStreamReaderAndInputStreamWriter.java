package cn.com.coderZoe.Module1IO;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author yhs
 * @date 2020/4/3 21:07
 * @description
 */
public class Class15InputStreamReaderAndInputStreamWriter {
    /*
    * 笔记
    * InputStreamReader和InputStreamWriter是转换流 目的是将字节流转换为字符流 他们本身就是字符流 是Reader和Writer的子类
    * 当然他也是处理流，接受字节流的节点流来构造自己，做了转换
    * 除此以外，将字节流转为字符流可以指定字符集（编码规则） InputStreamReader(InputStream in,String charSetName)
    * 因为对于字符流，处理起来比字节流更简单，比如加上了BufferedReader和BufferedWriter可以逐行读写
     */

    public static void main(String[] args) {
        //操作System.in和System.out
        //System.in和System.out是字节流
//        inputLineAndOutputLine();

        //操作网络流，将网络流读来的字节流进行输出
        netByteStream();

        //操作网络流，将网络流读来的字节流转化为字符流再输出进行输
        netByteStreamToInputStreamReader();
    }

    public static void inputLineAndOutputLine(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            //循环获取键盘输入内容，打印此内容
            String line = null;
            while(!(line = reader.readLine()).equals("1")){
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        }catch (Exception e){
            System.out.println("操作异常");
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void netByteStream(){
        try {
            //字节输入流
            InputStream in = new URL("http://www.baidu.com").openStream();
            //输出字节流 可以看出来有乱码 也不能按行输出
            byte[] buffered = new byte[1024];
            int len = -1;
            while((len = in.read(buffered)) != -1){
                System.out.println(new String(buffered));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void netByteStreamToInputStreamReader(){
        try {
            InputStream in = new URL("http://www.baidu.com").openStream();
            //将字节流转为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
