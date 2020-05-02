package cn.com.coderZoe.Module4WebServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author yhs
 * @date 2020/4/30 21:44
 * @description 写一个scoketServer用来接受HTTP请求
 */
public class Class6SocketServer {
    /**
     * 我们直到HTTP是应用层协议 但他的底层也是TCP协议
     * 所有我们可以手写一个SocketServer用来模拟服务器接收HTTP请求协议
     */

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Class6SocketServer class6SocketServer = new Class6SocketServer();
        class6SocketServer.start();
    }

    public void start(){
        try {
            this.serverSocket = new ServerSocket(10240);
            this.receive();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("服务器开启失败");
        }
    }

    public void receive(){
        try {
            Socket socket = serverSocket.accept();
            System.out.println("一个客户端连接成功");

            process(socket);
//            InputStream inputStream = socket.getInputStream();
//            byte[] bytes = new byte[1024*1024];
//            int len = inputStream.read(bytes);
//            System.out.println(new String(bytes,0,len));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("客户端连接失败");
        }
    }

    /**
     * @data: 2020/04/30 22:29
     * @author: yhs
     * @return:
     * @description: 处理请求
     */
    public void process(Socket socket){

        //返回内容
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head><title>这是返回信息</title></head>");
        content.append("<body>你收到了我的返回信息</body>");
        content.append("</html>");
        int len = content.toString().getBytes().length;

        StringBuilder response = new StringBuilder();
        String blank = " ";
        String CRLF = "\r\n";
        //响应行:HTTP/1.1 200 OK
        response.append("HTTP/1.1").append(blank).append(200).append(blank).append("OK").append(CRLF);
        //响应头
        //Date: Tue, 11 Jul 2000 18:23:51 GMT
        response.append("Date:").append(new Date()).append(CRLF);
        //Server:apache tomcat
        response.append("Server:").append("apache tomcat").append(CRLF);
        //Content-Type: text/html; charset=GB2312
        response.append("Content-Type:").append("text/html;charset=GBK").append(CRLF);
        //Content-Length: 80
        response.append("Content-Length:").append(len).append(CRLF);
        //请求头和正文之间有一个空行！！
        response.append(CRLF);
        response.append(content);
        returnRes(socket,response.toString());
    }

    /**
     * @param response 响应
     * @data: 2020/04/30 22:29
     * @author: yhs
     * @return:
     * @description: 返回响应
     */
    public void returnRes(Socket socket,String response){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(response);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("传输数据失败");
        }
    }

    public void end(){

    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
