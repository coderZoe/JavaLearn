package cn.com.coderZoe.Module3Net;

/**
 * @author yhs
 * @date 2020/4/20 20:17
 * @description
 */
public class Class3Communication1 {
    public static void main(String[] args) {
//        new Class3UDPCommunicateSend(3333,"localHost",9999).run();
        new Thread(new Class3UDPCommunicateSend(3333,"localHost",9999)).start();
        new Thread(new Class3UDPCommunicateReceive(5555)).start();
    }
}
