package cn.com.coderZoe.Module3Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yhs
 * @date 2020/4/20 20:59
 * @description
 */
public class Class4TCP {
    /*
    * TCP传输是安全可靠的
    * 效率比UDP低
    * 底层不是包传输 是IO流
    * 分服务器和客户端
    * ServerSocket创建服务器的Socket
    * Socket serverSocket.accept(); 阻塞式等待 一旦连接上客户端 返回该客户端的Socket
     */

    public static void main(String[] args) {
//        Server.main();
//        Client.main();
    }
}

class Server{
    public static void main(String[] args) {
        try {
            System.out.println("---------------server--------------");
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket client = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            System.out.println(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Client{
    public static void main(String[] args) {
        try {
            System.out.println("---------------client--------------");
            Socket socket = new Socket("localHost",9999);
            String line = "你好";
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("你好");
            dataOutputStream.flush();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
