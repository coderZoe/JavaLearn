package cn.com.coderZoe.Module3Net;

import java.io.*;
import java.net.*;

/**
 * @author yhs
 * @date 2020/4/16 22:04
 * @description
 */
public class Class3UDPCommunicateSend implements Runnable{
    private DatagramSocket socket;
    private SocketAddress address;

    public Class3UDPCommunicateSend(int srcPort,String dstIp,int dstPort) {
        try {
            this.socket = new DatagramSocket(srcPort);
            this.address = new InetSocketAddress(dstIp,dstPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                String line = reader.readLine();
                byte[] data = line.getBytes();
                DatagramPacket packet = new DatagramPacket(data,data.length,this.address);
                this.socket.send(packet);
                if(line.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socket.close();
    }
}
