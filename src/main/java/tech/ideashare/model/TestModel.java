package tech.ideashare.model;

import java.math.BigDecimal;
import java.util.Date;

public class TestModel{
    private String name;
    private Integer id;
    private Date time;
    private Boolean changeFlag;
    private BigDecimal money;
    private Long totalNum;

    public Long getTotalNum(){
        return totalNum;
    }

    public TestModel setTotalNum (Long totalNum){
        this.totalNum = totalNum;
        return this;
    }


    public Boolean getChangeFlag(){
        return changeFlag;
    }

    public TestModel setChangeFlag(Boolean changeFlag){
        this.changeFlag = changeFlag;
        return this;
    }

    public BigDecimal getMoney(){
        return money;
    }

    public TestModel setMoney(BigDecimal money){
        this.money = money;
        return this;
    }
    
    public String getName() {
        return name;
    }

    public TestModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public TestModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public TestModel setTime(Date time) {
        this.time = time;
        return this;
    }
}