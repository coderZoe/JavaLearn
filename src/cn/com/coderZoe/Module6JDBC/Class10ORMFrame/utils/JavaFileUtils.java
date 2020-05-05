package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.utils;

import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.ColumnInfo;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.FieldAndGetSet;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean.TableInfo;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core.DataBaseManager;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core.MySqlTypeConvertor;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core.TableContext;
import cn.com.coderZoe.Module6JDBC.Class10ORMFrame.core.TypeConvertor;
import javafx.scene.control.Tab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/5/5 11:45
 * @description 封装Java文件操作 生成数据库Java对象源码
 */
public class JavaFileUtils {

    public final static String BLANK = " ";
    public final static String TABS = "\t";
    public final static String CRLF = "\r\n";
    public final static String END = ";";
    /**
     * @param columnInfo    列信息
     * @param typeConvertor 类型转换器
     * @data: 2020/05/05 15:08
     * @author: yhs
     * @return: {@link FieldAndGetSet } Java属性与Set和Get源码
     * @description:  根据数据库字段信息生成Java属性信息
     */
    public static FieldAndGetSet createFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor typeConvertor){

        //Field
        FieldAndGetSet fieldAndGetSet = new FieldAndGetSet();
        String javaFiledType = typeConvertor.dateBaseToJava(columnInfo.getDataType());
        fieldAndGetSet.setFieldInfo(TABS+"private"+BLANK+javaFiledType+BLANK+columnInfo.getName()+END+CRLF);

        //Getter
        StringBuilder getter = new StringBuilder();
        getter.append(TABS+"public"+BLANK+javaFiledType+BLANK+"get"+StringUtils.firstCharToUppercase(columnInfo.getName())+
                "()"+BLANK+"{"+CRLF);
        getter.append(TABS+TABS+"return"+BLANK+"this."+columnInfo.getName()+END+CRLF);
        getter.append(TABS+"}"+CRLF);
        fieldAndGetSet.setGetInfo(getter.toString());

        //Setter
        StringBuilder setter = new StringBuilder();
        setter.append(TABS+"public"+BLANK+"void"+BLANK+"set"+StringUtils.firstCharToUppercase(columnInfo.getName())+
                "("+javaFiledType+BLANK+columnInfo.getName()+")"+BLANK+"{"+CRLF);
        setter.append(TABS+TABS+"this."+columnInfo.getName()+BLANK+"="+BLANK+columnInfo.getName()+END+CRLF);
        setter.append(TABS+"}"+CRLF);
        fieldAndGetSet.setSetInfo(setter.toString());

        return fieldAndGetSet;
    }

    public static String createJavaSrc(TableInfo tableInfo,TypeConvertor typeConvertor){
        Map<String,ColumnInfo> columnInfoMap = tableInfo.getColumnInfoMap();
        List<FieldAndGetSet> fieldAndGetSetList = new ArrayList<>();

        //将列信息转化为源码信息
        for(ColumnInfo columnInfo:columnInfoMap.values()){
            fieldAndGetSetList.add(createFieldGetSetSRC(columnInfo,typeConvertor));
        }

        StringBuilder stringBuilder = new StringBuilder();
        //package语句
        stringBuilder.append("package"+BLANK+ DataBaseManager.getConfiguration().getPoPackage()+END+CRLF+CRLF);
        //import语句
        stringBuilder.append("import java.sql.*"+END+CRLF);
        stringBuilder.append("import java.util.*"+END+CRLF+CRLF);
        //生成类声明
        stringBuilder.append("public class"+BLANK+StringUtils.firstCharToUppercase(tableInfo.getTableName())+BLANK+"{"+CRLF+CRLF);
        //生成属性列表
        for(FieldAndGetSet fieldAndGetSet:fieldAndGetSetList){
            stringBuilder.append(fieldAndGetSet.getFieldInfo());
        }
        stringBuilder.append(CRLF);
        //生成get方法列表
        for(FieldAndGetSet fieldAndGetSet:fieldAndGetSetList){
            stringBuilder.append(fieldAndGetSet.getGetInfo());
        }
        stringBuilder.append(CRLF);
        //生成set方法列表
        for(FieldAndGetSet fieldAndGetSet:fieldAndGetSetList){
            stringBuilder.append(fieldAndGetSet.getSetInfo());
        }
        stringBuilder.append(CRLF);
        //结束
        stringBuilder.append("}"+CRLF);

        return stringBuilder.toString();
    }

    public static void writeJavaSrc(TableInfo tableInfo,TypeConvertor typeConvertor){
        String src = createJavaSrc(tableInfo,typeConvertor);

        String srcPath = DataBaseManager.getConfiguration().getSrcPath();
        String packagePath = DataBaseManager.getConfiguration().getPoPackage().replaceAll("\\.","/");
        String path = srcPath+packagePath+"/"+StringUtils.firstCharToUppercase(tableInfo.getTableName()+".java");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));){
            bufferedWriter.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ColumnInfo columnInfo = new ColumnInfo("name","varchar",0);
//        FieldAndGetSet fieldAndGetSet = createFieldGetSetSRC(columnInfo,new MySqlTypeConvertor());
//        System.out.println(fieldAndGetSet.getFieldInfo()+fieldAndGetSet.getGetInfo()+fieldAndGetSet.getSetInfo());

        Map<String,TableInfo> tableInfoMap = TableContext.getTableInfoMap();
        for(TableInfo tableInfo:tableInfoMap.values()){
            writeJavaSrc(tableInfo,new MySqlTypeConvertor());
//            System.out.println(createJavaSrc(tableInfo,new MySqlTypeConvertor()));
        }

    }
}
