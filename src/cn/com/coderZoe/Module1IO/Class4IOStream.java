package cn.com.coderZoe.Module1IO;

/**
 * @author yhs
 * @date 2020/3/29 21:21
 * @description
 */
public class Class4IOStream {
    /*
    *笔记
    * 四大抽象类 InputStream OutputStream Reader Writer
    * 1.InputStream(字节流)常用方法
    * read()从输入流读取数据的下一个字节 read(byte[] b)从输入流读取一些字节数，并将它们存储到缓冲区b
    * close() 关闭此输入流并释放与流相关联的任何系统资源
    * 2.OutputStream(字节流)常用方法
    * write(int b) 将指定的字节写入此输出流
    * write(byte[] b) 将 b.length字节从指定的字节数组写入此输出流
    * write(byte[] b, int off, int len) 从指定的字节数组写入 len个字节，从偏移 off开始输出到此输出流
    * flush() 刷新此输出流并强制任何缓冲的输出字节被写出 关闭前必刷新
    * close() 关闭此输出流并释放与此流相关联的任何系统资源
    * 3.Reader(字符流 只能处理字符)常用方法
    * close()与上面相同
    * read()读一个字符
    * read(char[] cbuf) 将字符读入数组
    * read(char[] cbuf, int off, int len) 将字符读入数组的一部分
    * 4.Writer(字符流 只能处理字符)常用方法
    * close()与上面相同
    * write(String) 写一个字符串
    * write(char[] cbuf) 写入一个字符数组
    * write(String str, int off, int len) 写一个字符串的一部分
    * write(char[] cbuf, int off, int len) 写入字符数组的一部分
    * append(char c) 将指定的字符附加到此作者
    * append(CharSequence csq) 将指定的字符序列附加到此作者
     * flush() 刷新流
     */

    public static void main(String[] args) {

    }
}
