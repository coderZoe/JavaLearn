package cn.com.coderZoe.Module4WebServer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yhs
 * @date 2020/4/26 19:50
 * @description
 */
public class Class1Reflection {
    /*
    * 笔记
    * 反射 反射的作用 通过Class对象 得到原来的类的信息 能够动态的加载类
    * 1.Class类的成员变量：
    * a.Field:代表对应实体类的成员变量
    * b.Constrcutor:代表对应实体类的构造器
    * c.Method:代表对应实体类的成员方法
    *
    * 2.获取Class对象的方法：
    * a.Object类的getClass()方法
    * b.数据类型的静态属性class 类.class
    * c.Class类中的静态方法 forName public static Class forName(String className)  Class a = Class.forName("cn.com.coderZoe.Module4WebServer")
    *
    * 3.获取成员变量并使用
    * step1:获得Class对象
    * step2:通过Class对象获得Constructor对象
    * step3:Object obj = Constructor.newInstance() 创建对象
    * step4:Field field = Class.getField("指定变量名")获取单个成员变量对象
    * step5:field.set(obj,"") 为obj对象的field字段赋值
    * 如果需要访问私有或者默认修饰的成员变量 Class.getDeclaredField()获取该成员变量对象 setAccessible() 暴力访问
    *
    * 4.通过反射调用成员方法
    * step1:获取Class对象
    * step2:通过Class对象获取Constructor对象
    * step3:Object obj = Constructor.newInstance()创建对象
    * step4:通过Class对象获取Method对象  ------getMethod("方法名")
    * step5:Method对象调用invoke方法实现功能
    * 如果调用的是私有方法那么需要暴力访问 getDeclaredMethod()获取该成员方法 setAccessible()暴力访问
     */

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class demoClass = Class.forName("cn.com.coderZoe.Module4WebServer.Demo");
        Demo demo = (Demo) demoClass.getConstructor().newInstance();
        Field field = demoClass.getDeclaredField("id");
        field.setAccessible(true);
        System.out.println(field);

        Method method = demoClass.getMethod("getId");
    }

}

class Demo{
    private int id;

    public Demo() {
    }

    public Demo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
