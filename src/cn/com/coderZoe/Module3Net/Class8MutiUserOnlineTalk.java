package cn.com.coderZoe.Module3Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yhs
 * @date 2020/4/21 19:53
 * @description
 */
public class Class8MutiUserOnlineTalk {
    public static void main(String[] args) {

    }
}

class ClientTalk1{
    public static void main(String[] args) throws IOException {
        System.out.println("请输入连接用户名：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Socket socket = new Socket("localHost",9999);
        new Thread(new Send(socket.getOutputStream(),name)).start();
        new Thread(new Receive(socket.getInputStream())).start();
    }
}

class ClientTalk2{
    public static void main(String[] args) throws IOException {
        System.out.println("请输入连接用户名：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Socket socket = new Socket("localHost",9999);
        new Thread(new Send(socket.getOutputStream(),name)).start();
        new Thread(new Receive(socket.getInputStream())).start();
    }
}

class ClientTalk3{
    public static void main(String[] args) throws IOException {
        System.out.println("请输入连接用户名：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Socket socket = new Socket("localHost",9999);
        new Thread(new Send(socket.getOutputStream(),name)).start();
        new Thread(new Receive(socket.getInputStream())).start();
    }
}

class ClientTalk4{
    public static void main(String[] args) throws IOException {
        System.out.println("请输入连接用户名：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Socket socket = new Socket("localHost",9999);
        new Thread(new Send(socket.getOutputStream(),name)).start();
        new Thread(new Receive(socket.getInputStream())).start();
    }
}

class ServerTalk{
    private static CopyOnWriteArrayList<Channel> allClient = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            Socket client = serverSocket.accept();
            String name = new DataInputStream(client.getInputStream()).readUTF();
            System.out.println("收到客户端"+name+"的连接");
            Channel clientChannel = new Channel(client,name);
            allClient.add(clientChannel);
            new Thread(clientChannel).start();

        }
    }

    public static CopyOnWriteArrayList<Channel> getAllClient() {
        return allClient;
    }
}

class Channel implements Runnable{
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket client;
    private String name;
    public Channel(Socket client,String name) {
        try {
            this.client = client;
            this.dataInputStream = new DataInputStream(client.getInputStream());
            this.dataOutputStream = new DataOutputStream(client.getOutputStream());
            this.name = name;
            writeMessage("欢迎你的到来:"+this.name);
            writeMessageToOthers("欢迎"+this.name+"来到聊天室");
        } catch (IOException e) {
            release();
        }
    }

    @Override
    public void run() {
        String message = "";
        while (!message.equals("bye")){
            message = readMessage();
            if(!message.equals("")){
                writeMessageToOthers(message);
            }
        }
    }

    public String readMessage(){
        String message = "";
        try {
            message = dataInputStream.readUTF();
        } catch (IOException e) {
            release();
        }
        return message;
    }

    public void writeMessage(String message){
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            release();
        }
    }

    public void writeMessageToOthers(String message){
        for(Channel other:ServerTalk.getAllClient()){
            if(other==this){
                continue;
            }else {
                String sendInfo = "收到"+this.name+"的消息:"+message;
                other.writeMessage(sendInfo);
            }
        }
    }

    public void release(){
        NetUtils.close(this.dataOutputStream,this.dataInputStream,this.client);
        ServerTalk.getAllClient().remove(this);
        writeMessageToOthers(this.name+"离开了群聊");
    }
}

class Send implements Runnable{
    private BufferedReader reader;
    private DataOutputStream  dataOutputStream;
    private String name;

    public Send(OutputStream outputStream,String name) {
        this.dataOutputStream =new DataOutputStream(outputStream);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.name = name;
        try {
            dataOutputStream.writeUTF(name);
        } catch (IOException e) {
            release();
        }
    }

    @Override
    public void run() {
        String line = "";
        do{
            try {
                line = reader.readLine();
                if(!line.equals("")){
                    dataOutputStream.writeUTF(line);
                }
            } catch (IOException e) {
                release();
                e.printStackTrace();
            }
        }while (!line.equals("bye"));
    }

    public void release(){
        NetUtils.close(this.dataOutputStream,this.reader);
    }
}

class Receive implements Runnable{
    private DataInputStream dataInputStream;

    public Receive(InputStream inputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
    }

    @Override
    public void run() {
        String line = "";
        do{
            try {
                line = dataInputStream.readUTF();
                if(!line.equals("")){
                    System.out.println(line);
                }
            } catch (IOException e) {
                release();
            }
        }while (!line.equals("bye"));
    }

    public void release(){
        NetUtils.close(this.dataInputStream);
    }
}
