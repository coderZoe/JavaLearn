package cn.com.coderZoe.Module4WebServer;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.print.Doc;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yhs
 * @date 2020/4/26 20:47
 * @description
 */
public class Class2XMLParsing {
    /*
    * 笔记
    * 程序读取XML参数 通过更改配置的XML达到更改程序的目的
    * XML解析：读取XML中的数据
    * XML目前有两种解析方式dom解析(文档对象模型)和sax解析
    * XML应用程序不直接操作XML解析，XML解析是由XML解析器操作的，然后应用程序操作解析器解析出来的dom或sax
    * 常用的解析开发包有三种：
    * Jaxp(jdk自带) Jdom dom4j(用的最多)
    *
    * dom解析：
    * DOM解析会把XML文档加载到内存中，生成DOM树的元素都是以对象的形式存在的,我们操作这些对象就能够操作XML文档了
    * 通过操作dom，可以达到对任意节点的CRUD操作(与js操作dom基本相同)
    *
    * sax解析：
    * sax是一种推式的机制,你创建一个sax 解析器,解析器在发现xml文档中的内容时就告诉你(把事件推给你). 如何处理这些内容，由程序员自己决定
    * 具体来说就是解析器一点点不断的解析 解析到一个节点 就会触发一个函数 这个函数是接口定好的，你需要根据自己的需求来实现，这个接口实现类叫事件处理器
    * 事件处理器继承父类DefaultHandler
    *
    * dom4j dom的问题在于太吃内存，而sax的问题在于无法CRUD 只能读取
    *
    *
     */

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DomTest.opDom("op.xml");
        SaxTest.opSax();
    }
}

/**
 * @data: 2020/04/26 22:33
 * @author: yhs
 * @description: 通过dom操作xml
 */
class DomTest{
    public static void opDom(String xmlPath) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        //step1:获取解析工厂
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //step2:从解析工厂获取解析器对象
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //step3:得到XML流对象
        File file = new File(xmlPath);
        //step4:用解析工厂解析XML文档得到document对象
        Document document = documentBuilder.parse(file);

        //后续所有的CRUD操作都是基于document
        //1.遍历
        DomTest.traverse(document);
        //2.查询
        DomTest.query(document,"guangzhou");
        //3.插入
        DomTest.add(document);
        DomTest.delete(document,"beijing");
        //修改
        DomTest.modify(document,"henan","信阳");

        //保存操作后的dom到本地
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document),new StreamResult("result.xml"));
    }


    /**
     * @param node 节点
     * @data: 2020/04/26 22:44
     * @author: yhs
     * @return:
     * @description: 递归遍历dom
     */
    private static void traverse(Node node){
        //判断是否是元素节点
        if(node.getNodeType()==Node.ELEMENT_NODE){
            System.out.println(node.getNodeName());
        }
        //如果不是 说明是一个有子节点的节点
        //获得所有子节点
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            //item方法类似于list的get方法
            traverse(nodeList.item(i));
        }
    }


    /**
     * @param document 文档
     * @param nodeName 节点名称
     * @data: 2020/04/26 23:15
     * @author: yhs
     * @return:
     * @description: 查询某个具体的节点
     */
    public static void query(Document document,String nodeName){
        NodeList nodeList = document.getElementsByTagName(nodeName);
        for(int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeList.item(i).getTextContent());
        }
    }

    /**
     * @param document 文档
     * @data: 2020/04/26 23:17
     * @author: yhs
     * @return:
     * @description: 在某个节点内增加(或插入)另一个节点
     */
    public static void add(Document document){
        //创建节点
        Element element = document.createElement("henan");
        element.setTextContent("河南");

        //尾部追加
        Node parentNode = document.getElementsByTagName("china").item(0);
        parentNode.appendChild(element);

        //插入
        Element element1 = document.createElement("henan");
        element.setTextContent("河南");
        Node element2 = document.getElementsByTagName("guangzhou").item(0);
        parentNode.insertBefore(element1,element2);

        traverse(document);
    }


    /**
     * @param document 文档
     * @param nodeName 节点名称
     * @data: 2020/04/26 23:31
     * @author: yhs
     * @return:
     * @description: 删除某个具体的节点
     */
    public static void delete(Document document,String nodeName){
        Node node = document.getElementsByTagName(nodeName).item(0);
        node.getParentNode().removeChild(node);
        traverse(document);
    }

    /**
     * @param document    文档
     * @param nodeName    节点名称
     * @param textContent 文本内容
     * @data: 2020/04/26 23:34
     * @author: yhs
     * @return:
     * @description: 修改节点文本内容
     */
    public static void modify(Document document,String nodeName,String textContent){
        Node node = document.getElementsByTagName(nodeName).item(0);
        node.setTextContent(textContent);
    }
}

/**
 * @data: 2020/04/27 08:51
 * @author: yhs
 * @description: 通过sax解析XML
 */
class SaxTest{
    public static void opSax() throws ParserConfigurationException, SAXException, IOException {
        //step1:获得解析器的构造工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //step2:通过构造工厂得到解析器对象
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //解析
        saxParser.parse(new File("op.xml"),new MyHandler());
    }
}

/**
 * @data: 2020/04/27 09:01
 * @author: yhs
 * @description: 事件处理器 用于响应sax解析
 */
class MyHandler extends DefaultHandler{

    //开始解析
    @Override
    public void startDocument() throws SAXException {
        System.out.println("我要开始解析啦！");
    }

    //结束解析
    @Override
    public void endDocument() throws SAXException {
        System.out.println("我得解析结束啦！！");
    }

    //遇到标签
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("我是开始标签<"+qName+">");
    }

    //结束标签

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("我是结束标签</"+qName+">");
    }

    //遇到内容文本

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch,start,length));
    }
}

/**
 * @data: 2020/04/27 09:04
 * @author: yhs
 * @description:
 */
class Dom4jTest{
    public static void opDom4j() throws DocumentException {

        //解析得到xml的document
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = reader.read(new File("op.xml"));  //可以看到这里的document改变了

        //查询
        Dom4jTest.query(document,"beijing");
    }

    /**
     * @param document 文档
     * @param nodeName 节点名称
     * @data: 2020/04/27 09:19
     * @author: yhs
     * @return:
     * @description: dom4j的查询
     */
    public static void query(org.dom4j.Document document,String nodeName){
        org.dom4j.Element rootElement = document.getRootElement();
        org.dom4j.Element element = rootElement.element(nodeName);
        String context = element.getText();
        System.out.println("标签名:"+element.getName()+"内容:"+context);
    }

    /**
     * @param document    文档
     * @param nodeName    节点名称
     * @param contentText 内容的文本
     * @data: 2020/04/27 09:21
     * @author: yhs
     * @return:
     * @description: dom4j增加节点
     */
    public static void add(org.dom4j.Document document,String nodeName,String contentText){

        //创建新元素
        org.dom4j.Element newElement = DocumentHelper.createElement(nodeName);
        newElement.setText(contentText);

        //获得根节点 将新元素挂在根节点下面
        org.dom4j.Element rootElement = document.getRootElement();
        rootElement.add(newElement);
    }

    /**
     * @param document 文档
     * @param nodeName 节点名称
     * @data: 2020/04/27 09:27
     * @author: yhs
     * @return:
     * @description: dom4j删除节点
     */
    public static void delete(org.dom4j.Document document,String nodeName){

    }
}
