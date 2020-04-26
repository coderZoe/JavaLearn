package cn.com.coderZoe.Module3Net;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author yhs
 * @date 2020/4/16 20:55
 * @description
 */

public class Class2UDPClient {
    /*
    * 笔记
    * Socket是传输层给应用层打开的接口 传输层有两个协议UDP和TCP
    * UDP非面向连接 不安全 可能存在丢包的情况 数据不能太大 但同时也比较高效
    * 每个数据发送单元被统一封装成数据包的形式 发送方将数据包发送到网络中 数据包在网络中寻找目的地
    * DatagramSocket 基于UDP的socket编程 用于发送或接收数据的数据包
    * DatagramSocket(SocketAddress bindaddr) 创建一个数据报套接字，绑定到指定的本地套接字地址。
    * void receive(DatagramPacket p) 从此套接字接收数据报包。
    * void send(DatagramPacket p) 从此套接字发送数据报包。

    * DatagramPacket 数据包
    * DatagramPacket(byte[] buf, int offset, int length, SocketAddress address) 构造用于发送长度的分组数据报包 length具有偏移offset 指定主机上到指定的端口号。
     */

    public static void main(String[] args) throws IOException {
        /*
        * UDP发送数据
        * 1.使用DatagramSocket 指定IP和端口 创建发送端
        * 2.封装数据 转成字节数组 封装成DatagramPacket
        * 3.send() 发送包裹
         */

        System.out.println("准备发送数据");
        DatagramSocket socket = new DatagramSocket(2000);
        byte[] bytes = FileUtils.readFileToByteArray(new File("abc.txt"));
        SocketAddress address = new InetSocketAddress("localHost",10000);   //这个SocketAddress是封装在packet里 端口和IP都是发送给目的地的端口和IP
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length,address);
        socket.send(packet);
        socket.close();
    }
}
