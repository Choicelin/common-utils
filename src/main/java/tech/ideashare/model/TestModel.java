package tech.ideashare.model;

import java.util.Date;

public class TestModel{
    private String name;
    private Integer id;
    private Date time;
    
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