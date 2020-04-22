package cn.com.coderZoe.Net;

import java.io.Closeable;

/**
 * @author yhs
 * @date 2020/4/21 20:14
 * @description
 */
public class NetUtils {
    public static void close(Closeable... target) {
        for(Closeable closeable:target){
            try {
                if(closeable!=null){
                    closeable.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
