package tech.ideashare.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by lixiang on 11/07/2017.
 */
public class PostRecord {

    private String tid;
    private String title;
    private String content;
    private String tbName;



    public PostRecord() {
    }

    public PostRecord(String tid, String title, String content, String tbName) {
        this.tid = tid;
        this.title = title;
        this.content = content;
        this.tbName = tbName;
    }

    public String getTid() {
        return tid;
    }

    public SimpleStringProperty tidProperty() {
        return new SimpleStringProperty(tid);
    }

    public void setTid(String tid) {
        this.tid=tid;
    }

    public String getTitle() {
        return title;
    }

    public SimpleStringProperty titleProperty() {
        return new SimpleStringProperty(title);
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getContent() {
        return content;
    }

    public SimpleStringProperty contentProperty() {
        return new SimpleStringProperty(content);
    }

    public void setContent(String content) {
        this.content=content;
    }

    public String getTbName() {
        return tbName;
    }

    public SimpleStringProperty tbNameProperty() {
        return new SimpleStringProperty(tbName);
    }

    public void setTbName(String tbName) {
        this.tbName=tbName;
    }
}
