package cn.com.coderZoe.IO;

import java.io.UnsupportedEncodingException;

/**
 * @author yhs
 * @date 2020/3/24 20:00
 * @description
 */
public class Class3Encoding {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //编码
        String msg = "殷华盛";
        byte[] bytes = msg.getBytes();
        byte[] bytes1 = msg.getBytes("gbk");
        System.out.println(bytes.length);
        System.out.println(bytes1.length);

        //解码
        //String构造方法支持直接传byte数组，默认是按utf-8编码
        String msg1 = new String(bytes);

        //String(byte[] bytes, int offset, int length, Charset charset)
        String msg2 = new String(bytes1,0,bytes1.length,"gbk");
        System.out.println(msg1);
        System.out.println(msg2);

    }
}
