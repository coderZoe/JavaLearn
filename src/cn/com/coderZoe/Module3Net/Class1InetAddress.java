package cn.com.coderZoe.Module3Net;

import java.net.*;

/**
 * @author yhs
 * @date 2020/4/15 20:41
 * @description
 */
public class Class1InetAddress {

    /*
    * InetAddress类
    * 该类表示IP(Internet Protocol)地址
    * IP作用 定位一个节点(计算机)
    *
    * InetSocketAddress类 包含端口 用于Socket通信
    * 端口作用 定位节点上的程序(进程)
    * 公认端口：0-1023 比如80时HTTP的 21端口是FTP的
    * 其他公认：8080Tomcat  1521Orancle  3306MySQL
    * 命令行
    * netstat -ano 查看所有端口
    * netstat -ano|findstr "3306" 查看指定端口
    * tasklist|findstr "80" 查看指定进程
    *
    * URL
    * URL 定位程序上的资源
    * URI包含URL和URN
    * URL：协议+域名+端口+请求资源
     */

    public static void main(String[] args) throws UnknownHostException, MalformedURLException {

        //InetAddress类
        //得到本机的IP和计算机名
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        //根据域名获取IP
        InetAddress address1 = InetAddress.getByName("www.163.com");
        System.out.println(address1.getHostAddress());

        //根据IP得到InetAddress对象
        InetAddress address2 = InetAddress.getByName(address1.getHostAddress());
        System.out.println(address2.getHostName());

        //InetSocketAddress类
        InetSocketAddress socketAddress = new InetSocketAddress("localHost",3306);
        System.out.println(socketAddress.getHostName());
        System.out.println(socketAddress.getAddress());
        System.out.println(socketAddress.getPort());

        //URL
        URL url = new URL("http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_b471cc0ab6084f2.jpg");
        System.out.println("协议:"+url.getProtocol());
        System.out.println("域名："+url.getHost());
        System.out.println("请求资源"+url.getFile());
        System.out.println("请求资源"+url.getPath());
    }
}