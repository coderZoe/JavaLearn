package cn.com.coderZoe.Module3Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yhs
 * @date 2020/4/20 21:23
 * @description
 */
public class Class5TCPUpload {

    public static void main(String[] args) {

    }
}

class UserInfo implements Serializable {
    private String userName;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class UserClient{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String userName = reader.readLine();
        System.out.print("请输入密码:");
        String password = reader.readLine();
        UserInfo userInfo = new UserInfo(userName,password);
        Socket socket = new Socket("localHost",7777);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(userInfo);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String result =  dataInputStream.readUTF();
        System.out.println(result);
        objectOutputStream.close();
        socket.close();
    }
}

class UserServer{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(7777);
        Socket client = serverSocket.accept();
        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        UserInfo userInfo = (UserInfo) objectInputStream.readObject();
        if(userInfo.getUserName().equals("admin")&&userInfo.getPassword().equals("123")){
            dataOutputStream.writeUTF("成功");
        }else {
            dataOutputStream.writeUTF("失败");
        }

        objectInputStream.close();
    }
}
