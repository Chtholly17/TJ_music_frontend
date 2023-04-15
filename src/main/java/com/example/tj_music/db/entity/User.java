package com.example.tj_music.db.entity;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

/* @author chtholly
 * @date 2018/1/2
 * @description: user entity class for database table user, table user have four columns:
 * id, passwd, name, banned,greet, id is the primary key
 */
@Data
public class User {
    private Integer id;
    private String passwd;
    private String name;
    private boolean banned;
    private String greet;

    public User() {
    }

    public User(Integer id, String passwd, String name, boolean banned, String greet) {
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.banned = banned;
        this.greet = greet;
    }

    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setGreet(String greet) {
        this.greet = greet;
    }

    // getters
    public Integer getId() {
        return id;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getName() {
        return name;
    }

    public boolean getBanned() {
        return banned;
    }

    public String getGreet() {
        return greet;
    }
}
