package cn.com.coderZoe.Module1IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/5 11:38
 * @description
 */
public class Class18PrintStream {
    /*
    * PrintStream，打印流 也是一种装饰流，常用的比如System.out就是打印流 同时PrintStream是一种输出流
    * PrintStream常用的方法包括像print,println等
    * PrintStream的构造方法很强大
    * PrintStream(File file) 使用指定的文件创建一个新的打印流，而不需要自动换行（将流写入文件中）
    * PrintStream(File file, String csn) 使用指定的文件和字符集创建新的打印流，而不需要自动换行
    * PrintStream(OutputStream out) 创建一个新的打印流
    * PrintStream(OutputStream out, boolean autoFlush) 创建一个新的打印流
    * PrintStream(OutputStream out, boolean autoFlush, String encoding)创建一个新的打印流
    * PrintStream(String fileName) 使用指定的文件名创建新的打印流，无需自动换行
    * PrintStream(String fileName, String csn)
     */



    public static void main(String[] args) {
        try {
            PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream("target.txt")));
            printStream.println("测试打印流");
            printStream.println("他可以换行打印，使用的是println方法");
            printStream.flush();
            printStream.close();

            //更改System.out输出位置
            //System.out是一个PrintStream流，它默认的输出地址是控制台，我们可以改变他的输出位置
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("target.txt")))));
            System.out.println("这里已经改变了System.out的输出方向，这里输出的信息需要在target.txt文件中才能看到");
            System.out.flush();

            //重新改回System.out指向控制台输出
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)),true));
            System.out.println("控制台输出");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //PrintWriter处理字节的打印流 效果与PrintStream一样

    }
}
