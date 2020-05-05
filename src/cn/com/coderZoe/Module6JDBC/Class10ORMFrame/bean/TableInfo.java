package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean;

import java.util.List;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/5/5 11:54
 * @description 表信息
 */
public class TableInfo {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 所有字段的信息
     */
    private Map<String,ColumnInfo> columnInfoMap;

    /**
     * 主键
     */
    private ColumnInfo primaryKey;

    /**
     * 联合主键
     */
    private List<ColumnInfo>  primaryKeys;

    public TableInfo() {
    }

    public TableInfo(String tableName, Map<String, ColumnInfo> columnInfoMap, ColumnInfo primaryKey) {
        this.tableName = tableName;
        this.columnInfoMap = columnInfoMap;
        this.primaryKey = primaryKey;
    }

    public TableInfo(String tableName, Map<String, ColumnInfo> columnInfoMap, List<ColumnInfo> primaryKeys) {
        this.tableName = tableName;
        this.columnInfoMap = columnInfoMap;
        this.primaryKeys = primaryKeys;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnInfo> getColumnInfoMap() {
        return columnInfoMap;
    }

    public void setColumnInfoMap(Map<String, ColumnInfo> columnInfoMap) {
        this.columnInfoMap = columnInfoMap;
    }

    public ColumnInfo getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnInfo primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ColumnInfo> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<ColumnInfo> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }
}
