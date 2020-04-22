package cn.com.coderZoe.IO;

import java.io.*;

/**
 * @author yhs
 * @date 2020/4/4 10:41
 * @description
 */
public class Class16DataInputStreamAndDataOutputStream {
    /*
    * 笔记
    * DataInputStream和DataOutputStream是数据流，他也是一种装饰流（处理流），装饰流的构造是将一个节点流作为参数来构造
    * 主要作用是处理八大基本数据类型和字符串
    * DataInputStream 数据输入流允许应用程序以独立于机器的方式从底层输入流读取原始Java数据类型。 应用程序使用数据输出流来写入稍后可以被数据输入流读取的数据
    * 包含方法：
    * readBoolean()
    * readByte()
    * readChar()
    * readDouble()
    * readFloat()
    * readLong()
    * readShort()
    * readUTF()
    * DataOutputStream 数据输出流使应用程序以便携式方式将原始Java数据类型写入输出流。 然后应用程序可以使用数据输入流来读取数据
    * 包含方法：
    * writeBoolean(boolean v)将 boolean写入底层输出流作为1字节值
    * writeByte(int v) 将 byte作为1字节值写入底层输出流
    * writeBytes(String s) 将字符串作为字节序列写入基础输出流
    * writeChar(int v) 将 char写入底层输出流作为2字节值，高字节优先
    * writeDouble(double v) 双参数传递给转换 long使用 doubleToLongBits方法在类 Double ，然后写入该 long值基础输出流作为8字节的数量，高字节
    * writeFloat(float v)浮子参数的转换 int使用 floatToIntBits方法在类 Float ，然后写入该 int值基础输出流作为一个4字节的数量，高字节
    * writeInt(int v) 将底层输出流写入 int作为四字节，高位字节
    * writeLong(long v) 将 long写入底层输出流，为8字节，高字节为首
    * writeShort(int v) 将 short写入底层输出流作为两个字节，高字节优先
    * writeUTF(String str) 使用 modified UTF-8编码以机器无关的方式将字符串写入基础输出流
    * 数据类型的读取要保持顺序一致
     */

    public static void main(String[] args) {

        //DataInputStream DataOutputStream
        //先写入再读取
        /*
        * 分析过程：
        * 首先选择数据源，由于ByteArrayOutputStream输出地为内存，Java自己定好的不需要你管，所以这里不需要一个数据源变量
        * 然后选定节点流为ByteArrayOutputStream，外层套上BufferedOutputStream和DataOutputStream。
        * DataOutputStream装饰流的作用是读取基本数据类型，但这个输出流的核心还是ByteArrayOutputStream，
        * 所以还是读取到了ＢyteArrayOutputStream的内存里，根据toByteArray()读取内存转为字节
        * 这个输出字节其实装的就是DataOutputStream写入的基本数据
        * 将这个字节作为参数传给ByteArrayInputStream构造字节输入流，再套上BufferedInputStream和DataInputStream
        * 这样DataInputStream就能读取之前DataOutputStream写入的基本数据类型
        * 读取的核心是得有一个数据源，然后对数据源进行解析读取，这里输出流做的工作就是提供了一个数据源，而且是Data流的数据源
        * 这样就有了一个合理的数据源，再传给输入流，让输入流读取
        * 换句话说 DataInputStream对应的底层字节流都是同样由DataOutputStream写入构造出来的，要不然会异常或乱码
         */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        DataInputStream in = null;
        try {
            //写入构造数据源
            out.writeUTF("这是一个String");
            out.writeLong(17L);
            out.writeBoolean(false);
            out.writeDouble(97.5);
            //注意 这里一定要flush因为节点流的底层上套了一层缓冲流，缓冲区域比较大，不flush会写不进去
            out.flush();
            byte []srcByteArray = byteArrayOutputStream.toByteArray();

            //将数据源传给输入流，进行读取
            in = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(srcByteArray)));
            //这里很关键的是 读取顺序得和写入顺序相同
            System.out.println(in.readUTF());
            System.out.println(in.readLong());
            System.out.println(in.readBoolean());
            System.out.println(in.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //除此以外还可以用文件字节流作为节点流
        DataOutputStream outputStream  = null;
        DataInputStream inputStream = null;
        try {
            outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("data.txt"))));
            outputStream.writeUTF("这是一个字符串");
            outputStream.writeDouble(18.9);
            outputStream.writeLong(20L);
            outputStream.writeBoolean(false);
            outputStream.flush();
            inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("data.txt"))));
            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readDouble());
            System.out.println(inputStream.readLong());
            System.out.println(inputStream.readBoolean());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
