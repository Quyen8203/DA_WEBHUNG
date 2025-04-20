package com.example.be_MrHung.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id ;
    private String username ;
    private String password ;
    private String user_fullname ;
    private Date user_birthday ;
    private boolean user_gender ;
    private String user_email ;
    private String user_phone ;
    private int user_point;
    private String user_address ;

    public User() {
    }

    public User(int user_id, String username, String password, String user_fullname, Date user_birthday, boolean user_gender, String user_email, String user_phone, int user_point, String user_address) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.user_fullname = user_fullname;
        this.user_birthday = user_birthday;
        this.user_gender = user_gender;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_point = user_point;
        this.user_address = user_address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fulname) {
        this.user_fullname = user_fulname;
    }

    public Date getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
    }

    public boolean isUser_gender() {
        return user_gender;
    }

    public void setUser_gender(boolean user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUser_point() {
        return user_point;
    }

    public void setUser_point(int user_point) {
        this.user_point = user_point;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

}
