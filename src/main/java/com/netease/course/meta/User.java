package com.netease.course.meta;

public class User {
    private Integer userId;

    private String userName;

    private String userPassword;

    private Boolean userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean userType) {
        this.userType = userType;
    }
}