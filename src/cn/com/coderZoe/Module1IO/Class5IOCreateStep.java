package cn.com.coderZoe.Module1IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/1 21:01
 * @description
 */
public class Class5IOCreateStep {
    /*
    * 创建IO的步骤
    * 1.创建源，确定数据源
    * 2.选择流（四大流）
    * 3.具体操作（读写）
    * 4.释放流
     */

    public static void main(String[] args) {
        //1.创建源
        File file = new File("abc.txt");
        //2.选择流
        InputStream in = null;
        try {
            //文件字节流
            in = new FileInputStream(file);
            //3.操作
            //当读完文件后 最后会返回-1 可以循环读取文件
            int data;
            while ((data = in.read())!= -1){
                System.out.print((char)data);
            }
            System.out.println("");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                //4.释放资源
                if(in!=null){
                    in.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
