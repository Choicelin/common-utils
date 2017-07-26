package tech.ideashare.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by lixiang on 11/07/2017.
 */
public class TieBaPost {


    private String id;
    private String title;
    private String content;



    public TieBaPost() {
    }

    public TieBaPost(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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


}
