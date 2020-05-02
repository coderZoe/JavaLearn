package cn.com.coderZoe.Module4WebServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/4/29 21:54
 * @description
 */
public class Class4ServletMap {
    private static Map<String,String> servletMap = new HashMap<>();

    public Class4ServletMap(List<Servlet> servletList){
        for(Servlet servlet:servletList){
            for(String url:servlet.getUrlPath()){
                Class4ServletMap.servletMap.put(url,servlet.getClassName());
            }

        }
    }

    public static Map<String, String> getServletMap() {
        return Class4ServletMap.servletMap;
    }

}
