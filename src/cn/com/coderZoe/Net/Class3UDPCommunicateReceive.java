package cn.com.coderZoe.Net;

import java.io.IOException;
import java.net.*;

/**
 * @author yhs
 * @date 2020/4/16 22:01
 * @description
 */
public class Class3UDPCommunicateReceive implements Runnable{
    private DatagramSocket datagramSocket;

    public Class3UDPCommunicateReceive(int port) {
        try {
            this.datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                byte[] bytes = new byte[1024*10];
                DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
                this.datagramSocket.receive(packet);
                String line = new String(packet.getData(),0,packet.getLength());
                if(line.equals("bye")){
                    System.out.println("结束");
                    break;
                }
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.datagramSocket.close();
    }
}
