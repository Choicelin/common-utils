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

    public String getRemark() {
        return remark;
    }

    public MysqlField setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
