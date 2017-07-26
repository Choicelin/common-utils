package tech.ideashare.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by lixiang on 15/07/2017.
 */
public class TieBaTask {

    private String id;
    private String type;
    private String describe;
    private String status;


    public TieBaTask(String id, String type, String status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public SimpleStringProperty describeProperty() {
        return new SimpleStringProperty(describe);
    }

    public void setDescribe(String describe) {
        this.describe=describe;
    }

    public String getId() {
        return id;
    }

    public SimpleStringProperty idProperty() {
        return new SimpleStringProperty(id);
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getType() {
        return type;
    }

    public SimpleStringProperty typeProperty() {
        return new SimpleStringProperty(type);
    }

    public void setType(String type) {
        this.type=type;
    }

    public String getStatus() {
        return status;
    }

    public SimpleStringProperty statusProperty() {
        return new SimpleStringProperty(status);
    }

    public void setStatus(String status) {
        this.status=status;
    }
}
