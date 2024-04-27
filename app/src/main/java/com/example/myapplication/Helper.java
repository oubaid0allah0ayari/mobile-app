package com.example.myapplication;

public class Helper {
    String email,password,name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Helper() {
    }

    public Helper(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
