package cn.com.coderZoe.Module3Net;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * @author yhs
 * @date 2020/4/16 21:10
 * @description
 */
public class Class2UDPServer {
    /*
    * UDP接收端
    * 1.使用DatagramSocket指定端口 创建接收端
    * 2.准备容器 封装成DatagramPacket
    * 3.阻塞式接受包裹 receive
    * 4.分析数据 byte[] getData()  int getLength()
     */

    public static void main(String[] args) throws IOException {
        //准备容器接收数据
        System.out.println("准备接收数据");
        SocketAddress socketAddress = new InetSocketAddress("localHost",10000);

        DatagramSocket socket = new DatagramSocket(socketAddress);
        byte[] bytes = new byte[1024*10];
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
        socket.receive(packet);

        FileUtils.writeByteArrayToFile(new File("webTest.txt"),packet.getData(),0,packet.getLength());
        socket.close();
        System.out.println("接收成功");
    }
}
