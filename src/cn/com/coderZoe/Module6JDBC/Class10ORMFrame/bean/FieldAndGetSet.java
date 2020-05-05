package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean;

/**
 * @author yhs
 * @date 2020/5/5 15:02
 * @description 封装Java属性和Get Set源码
 */
public class FieldAndGetSet {
    private String fieldInfo;
    private String getInfo;
    private String setInfo;

    public FieldAndGetSet() {
    }

    public FieldAndGetSet(String fieldInfo, String getInfo, String setInfo) {
        this.fieldInfo = fieldInfo;
        this.getInfo = getInfo;
        this.setInfo = setInfo;
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }

}
