package tech.ideashare.model.generate_model;

/**
 * 数据库对应
 * @Author lixiang
 * @CreateTime 24/03/2018
 **/
public class MysqlField {

    //字段名称
    private String name;

    //字段类型
    private String type;

    //备注
    private String remark;

    public String getName() {
        return name;
    }

    public MysqlField setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public MysqlField setType(String type) {
        this.type = type;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public MysqlField setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
