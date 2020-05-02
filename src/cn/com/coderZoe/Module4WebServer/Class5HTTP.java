package cn.com.coderZoe.Module4WebServer;

/**
 * @author yhs
 * @date 2020/4/30 20:44
 * @description http协议
 */
public class Class5HTTP {
    /**
     * 笔记
     *
     * HTTP协议是客户端和服务器交流的一种通讯方式
     *
     * HTTP请求协议包含三个部分：请求行(方法 URI 协议版本) 请求头 和请求正文
     *
     * HTTP方法主要是GET和POST方法 get方法主要用来得到数据 post主要用来提交数据
     *
     * HTTP请求头包含：
     * 1.Accept: text/html,image/*    【浏览器告诉服务器，它支持的数据类型】
     * 2.Accept-Charset: ISO-8859-1	【浏览器告诉服务器，它支持哪种字符集】
     * 3.Accept-Encoding: gzip,compress 【浏览器告诉服务器，它支持的压缩格式】
     * 4.Accept-Language: en-us,zh-cn 【浏览器告诉服务器，它的语言环境】
     * 5.Host: www.it315.org:80【浏览器告诉服务器，它的想访问哪台主机】
     * 6.If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT【浏览器告诉服务器，缓存数据的时间】
     * 7.Referer: http://www.it315.org/index.jsp【浏览器告诉服务器，客户机是从那个页面来的---反盗链】
     * 8.User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)【浏览器告诉服务器，浏览器的内核是什么】
     * 9.Cookie【浏览器告诉服务器，带来的Cookie是什么】
     * 10.Connection: close/Keep-Alive  【浏览器告诉服务器，请求完后是断开链接还是保持链接】
     * 11.Date: Tue, 11 Jul 2000 18:23:51 GMT【浏览器告诉服务器，请求的时间】
     *
     *
     * HTTP响应也包含三个部分 状态行 响应头和响应正文
     *
     * 状态行包含HTTP版本号 状态码 状态叙述  如：HTTP/1.1 200 OK
     *
     * HTTP状态码：2XX一般是请求成功 3XX一般表示重定向 4XX表示客户端出错了 5XX服务器出错了
     *
     * HTTP响应头包含：
     * 1.Location: http://www.it315.org/index.jsp 【服务器告诉浏览器要跳转到哪个页面】
     * 2.Server:apache tomcat【服务器告诉浏览器，服务器的型号是什么】
     * 3.Content-Encoding: gzip 【服务器告诉浏览器数据压缩的格式】
     * 4.Content-Length: 80 【服务器告诉浏览器回送数据的长度】
     * 5.Content-Language: zh-cn 【服务器告诉浏览器，服务器的语言环境】
     * 6.Content-Type: text/html; charset=GB2312 【服务器告诉浏览器，回送数据的类型】
     * 7.Last-Modified: Tue, 11 Jul 2000 18:23:51 GMT【服务器告诉浏览器该资源上次更新时间】
     * 8.Refresh: 1;url=http://www.it315.org【服务器告诉浏览器要定时刷新】
     * 9.Content-Disposition: attachment; filename=aaa.zip【服务器告诉浏览器以下载方式打开数据】
     * 10.Transfer-Encoding: chunked  【服务器告诉浏览器数据以分块方式回送】
     * 11.Set-Cookie:SS=Q0=5Lb_nQ; path=/search【服务器告诉浏览器要保存Cookie】
     * 12.Expires: -1【服务器告诉浏览器不要设置缓存】
     * 13.Cache-Control: no-cache  【服务器告诉浏览器不要设置缓存】
     * 14.Pragma: no-cache   【服务器告诉浏览器不要设置缓存】
     * 15.Connection: close/Keep-Alive   【服务器告诉浏览器连接方式】
     * 16.Date: Tue, 11 Jul 2000 18:23:51 GMT【服务器告诉浏览器回送数据的时间】
     */
    public static void main(String[] args) {

    }
}
