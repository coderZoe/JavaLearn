package cn.com.coderZoe.Module3Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yhs
 * @date 2020/4/21 19:08
 * @description
 */
public class Class7OnlineChat {

    /*
    * 笔记
    * 使用TCP客户端实现聊天室
    * 实现群聊和私聊
     */

    public static void main(String[] args) {

    }
}

class TalkClient{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localHost",9999);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!line.equals("bye")){
            line = reader.readLine();
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(line);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println(dataInputStream.readUTF());
        }
        reader.close();
        socket.close();
    }
}

class TalkServer{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket client = serverSocket.accept();
        String line = "";
        while (!line.equals("bye")){
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            line = dataInputStream.readUTF();
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeUTF(line);
            dataOutputStream.flush();
        }

    }
}