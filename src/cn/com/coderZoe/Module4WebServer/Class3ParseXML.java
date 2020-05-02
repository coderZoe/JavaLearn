package cn.com.coderZoe.Module4WebServer;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author yhs
 * @date 2020/4/29 8:41
 * @description 解析XML 完成Servlet调用的第一步 web.xml解析
 */
public class Class3ParseXML {
    /**
     * 笔记
     * 我们都知道 所有浏览器的Http访问都是访问服务器的Servlet  而Servlet的配置信息都在web.xml中
     * 正常情况下 你只需要写servlet，并配置XML 通过servletName将url与你的servlet类进行
     * 至于加载匹配的过程 连接web框架已经帮你解决了 web框架所需要做的是：
     * 1.解析XML 通过浏览器的url路径找到你对应的servletClass
     * 2.应用反射 将找到的servletClass运行(比如运行doGet或doPost方法)
     *
     * 这里我们先实现第一步 就是解析web.xml 找到对应的servletClass
     */

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, DocumentException {

        //这里我们实现两种解析格式 分别是sax解析和dom4j解析

        //1.sax解析

        //step1:获得解析器的构造工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //step2:通过构造工厂得到解析器对象
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //解析
        saxParser.parse(new File("web.xml"),new SaxHandler());

        //2.dom4j解析

        List<Servlet> servletList = new ArrayList<>();

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("web.xml"));
        Element rootElement = document.getRootElement();

        //得到所有的servletList
        Iterator servletElementIterator = rootElement.elements("servlet").iterator();
        Iterator mappingElementIterator = rootElement.elements("servlet-mapping").iterator();

        //然后构造Servlet对象
        for(;servletElementIterator.hasNext()&&mappingElementIterator.hasNext();){
            Servlet servlet = new Servlet();

            Element servletElement = (Element) servletElementIterator.next();
            Element mappingElement = (Element) mappingElementIterator.next();

            Element servletName = servletElement.element("servlet-name");
            Element servletClass = servletElement.element("servlet-class");
            servlet.setName(servletName.getText());
            servlet.setClassName(servletClass.getText());

            Iterator urlPathIterator = mappingElement.elements("url-pattern").iterator();
            for(; urlPathIterator.hasNext() ;){
                Element urlPathElement = (Element) urlPathIterator.next();
                servlet.setUrlPath(urlPathElement.getText());
            }
            servletList.add(servlet);
        }

        for(Servlet servlet:servletList){
            System.out.println(servlet);
        }
    }
}
class Servlet{
    private String name;
    private String className;
    private Set<String> urlPath = new HashSet<>();
    public Servlet() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Set<String> getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath.add(urlPath);
    }

    @Override
    public String toString() {
        return "Servlet{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", urlPath=" + urlPath +
                '}';
    }
}
class SaxHandler extends DefaultHandler{
    private List<Servlet> servletList;
    private Servlet servlet;
    private String tag = null;
    @Override
    public void startDocument() throws SAXException {
        servletList = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        new Class4ServletMap(servletList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("servlet")){
            servlet = new Servlet();
        }
        tag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("servlet-mapping")){
            servletList.add(servlet);
        }
        this.tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if("servlet-name".equals(this.tag)){
            this.servlet.setName(new String(ch,start,length));
        }else if("servlet-class".equals(this.tag)){
            this.servlet.setClassName(new String(ch,start,length));
        }else if("url-pattern".equals(this.tag)){
            this.servlet.setUrlPath(new String(ch,start,length));
        }
    }

    public List<Servlet> getServletList() {
        return servletList;
    }
}


