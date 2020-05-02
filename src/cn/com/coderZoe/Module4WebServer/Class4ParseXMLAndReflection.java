package cn.com.coderZoe.Module4WebServer;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/4/29 21:46
 * @description xml解析和反射
 */
public class Class4ParseXMLAndReflection {

    /**
     * 将前面的xml解析和反射联合起来用
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //解析 得到servletMap
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File("web.xml"),new SaxHandler());

        Map<String,String> servletMap = Class4ServletMap.getServletMap();
        String url = "/second";
        String className = servletMap.get(url);
        Class servlet = Class.forName(className);
        InterfaceService service = (InterfaceService) servlet.getConstructor().newInstance();
        service.service();
    }
}
