package cn.com.coderZoe.Thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * @author yhs
 * @date 2020/4/9 20:03
 * @description
 */
public class Class3Callable {
    /*
    * 笔记
    * 线程实现的第三种方法，实现Callable接口,重写call方法
    * Callable接口的实现是JUC高并发编程的一部分
     */

    public static void main(String[] args) throws MalformedURLException, ExecutionException, InterruptedException {
        //创建三个线程任务
        CallThead callThead1 = new CallThead("https://www.baidu.com","baidu.html");
        CallThead callThead2 = new CallThead("http://www.163.com","wangyi.html");
        CallThead callThead3 = new CallThead("https://www.qq.com/","tecent.html");

        ExecutorService service = newFixedThreadPool(3);

        Future<Boolean> result1 = service.submit(callThead1);
        Future<Boolean> result2 = service.submit(callThead2);
        Future<Boolean> result3 = service.submit(callThead3);

        System.out.println("result1:"+result1.get()+"result2:"+result2.get()+"result3:"+result3.get());
        service.shutdownNow();
    }
}

class CallThead implements Callable<Boolean>{
    private URL url ;
    private File file ;

    public CallThead(String url, String file) throws MalformedURLException {
        this.url = new URL(url);
        this.file = new File(file);
    }

    @Override
    //call方法不同于run方法，call方法可以抛出异常，另外可以返回值
    public Boolean call() throws Exception {
        try {
            FileUtils.copyURLToFile(this.url,this.file);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

