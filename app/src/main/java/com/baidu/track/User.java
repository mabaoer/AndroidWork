package com.baidu.track;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String shenfen;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String shenfen) {
        this.username = username;
        this.password = password;
        this.shenfen = shenfen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShenfen() {
        return shenfen;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }
}
