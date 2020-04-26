package cn.com.coderZoe.Module3Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yhs
 * @date 2020/4/20 21:52
 * @description
 */
public class Class6MoreTCPClient {
    public static void main(String[] args) {

    }
}

class MoreUserServer{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000);
        while (true){
            new Thread(new Task(serverSocket.accept())).start();
        }
    }
}

class Client1{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String userName = reader.readLine();
        System.out.print("请输入密码:");
        String password = reader.readLine();
        UserInfo userInfo = new UserInfo(userName,password);
        Socket socket = new Socket("localHost",10000);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(userInfo);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String result =  dataInputStream.readUTF();
        System.out.println(result);
        objectOutputStream.close();
        socket.close();
    }
}
class Client2{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String userName = reader.readLine();
        System.out.print("请输入密码:");
        String password = reader.readLine();
        UserInfo userInfo = new UserInfo(userName,password);
        Socket socket = new Socket("localHost",10000);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(userInfo);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String result =  dataInputStream.readUTF();
        System.out.println(result);
        objectOutputStream.close();
        socket.close();
    }
}
class Client3{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String userName = reader.readLine();
        System.out.print("请输入密码:");
        String password = reader.readLine();
        UserInfo userInfo = new UserInfo(userName,password);
        Socket socket = new Socket("localHost",10000);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(userInfo);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String result =  dataInputStream.readUTF();
        System.out.println(result);
        objectOutputStream.close();
        socket.close();
    }
}
class Client4{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String userName = reader.readLine();
        System.out.print("请输入密码:");
        String password = reader.readLine();
        UserInfo userInfo = new UserInfo(userName,password);
        Socket socket = new Socket("localHost",10000);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(userInfo);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String result =  dataInputStream.readUTF();
        System.out.println(result);
        objectOutputStream.close();
        socket.close();
    }
}

class Task implements Runnable{
    private Socket client;

    public Task(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        DataOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(client.getInputStream());
            outputStream = new DataOutputStream(client.getOutputStream());
            UserInfo userInfo = (UserInfo) inputStream.readObject();
            if(userInfo.getUserName().equals("admin")&&userInfo.getPassword().equals("123")){
                outputStream.writeUTF("成功");
                outputStream.flush();
            }else {
                outputStream.writeUTF("失败");
                outputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}