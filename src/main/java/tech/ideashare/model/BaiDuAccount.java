package tech.ideashare.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by lixiang on 11/07/2017.
 */
public class BaiDuAccount {

    private String userName;
    private String password;
    private String cookie;

    public BaiDuAccount() {
    }

    public BaiDuAccount(String userName, String password, String cookie) {
        this.userName = userName;
        this.password = password;
        this.cookie = cookie;
    }

    public String getUserName() {
        return userName;
    }

    public SimpleStringProperty userNameProperty() {
        return new SimpleStringProperty(userName);
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }

    public String getPassword() {
        return password;
    }

    public SimpleStringProperty passwordProperty() {
        return new SimpleStringProperty(password);
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getCookie() {
        return cookie;
    }

    public SimpleStringProperty cookieProperty() {
        return new SimpleStringProperty(cookie);
    }

    public void setCookie(String cookie) {
        this.cookie=cookie;
    }
}
