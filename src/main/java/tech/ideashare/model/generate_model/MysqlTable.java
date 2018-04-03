package tech.ideashare.model.generate_model;

import java.util.List;

/**
 * 存的是数据库表对应的实体
 * @Author lixiang
 * @CreateTime 24/03/2018
 **/
public class MysqlTable {

    //表名
    private String tableName;
    //表里面的字段名
    private List<MysqlField> fieldList;

    public String getTableName() {
        return tableName;
    }

    public MysqlTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public List<MysqlField> getFieldList() {
        return fieldList;
    }

    public MysqlTable setFieldList(List<MysqlField> fieldList) {
        this.fieldList = fieldList;
        return this;
    }
}
