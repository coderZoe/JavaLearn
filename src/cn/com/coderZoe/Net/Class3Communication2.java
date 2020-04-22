package cn.com.coderZoe.Net;

/**
 * @author yhs
 * @date 2020/4/20 20:18
 * @description
 */
public class Class3Communication2 {
    public static void main(String[] args) {
//        new Class3UDPCommunicateReceive(9999).run();
        new Thread(new Class3UDPCommunicateSend(11111,"localHost",5555)).start();
        new Thread(new Class3UDPCommunicateReceive(9999)).start();
    }
}
