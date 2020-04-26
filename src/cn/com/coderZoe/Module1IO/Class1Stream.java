package cn.com.coderZoe.Module1IO;

/**
 * @author yhs
 * @date 2020/3/31 20:33
 * @description
 */
public class Class1Stream {
    /*
    * 笔记
    * 流的一端是文件源(文件网络等) 另一端是程序 流建立了两者的联系 流可以理解为管道
    * 以程序为中心 进来为输入流 出去为输出流 对输入流进行读 对输出流进行写
    * I.O包中最重要的是五个类 三个接口
    * 其中五个类是File InputStream OutputStream Reader和 Writer类
    * 三个接口是Closeable Flushable Serializable
    *
    * 流的分类
    * 一切以程序为中心 分为
    * 1.输入流（数据源到程序 InputStream Reader）和输出流(OutputStream Writer) sourceFile->InputStream->处理->OutputStream->targetFile
    * 2.按是否直接操作数据源 分为节点流和处理流 节点流：直接从数据源读数据或目的地写数据 处理流：不直接连接到数据源或目的地，是其他流进行封装，为了提高效率或简化操作
    * 3.按操作的数据分为字节流和字符流
    * 类里带Stream的都是字节流 比如FileInputStream，BufferedOutputStream,ByteArrayInputStream等
    * 类里带Reader和Writer的都是字符流 如FileReader FileWriter BufferReader BufferReader
    * 类里是FileStream或者ByteStream的都是节点流 其他是处理流
     */

    public static void main(String[] args) {

    }
}
