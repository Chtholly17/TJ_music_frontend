package com.example.tj_music.db.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer userId;
    private Date createTime;
    private String userStudentNumber;
    private String userNickname;
    private String userPassword;
    private String userSignature;
    private String userStatus;
    private String userProfileImageFilename;

    public User() {
    }

    public User(Integer userId, Date createTime, String userStudentNumber, String userNickname, String userPassword, String userSignature, String userStatus, String userProfileImageFilename) {
        this.userId = userId;
        this.createTime = createTime;
        this.userStudentNumber = userStudentNumber;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userSignature = userSignature;
        this.userStatus = userStatus;
        this.userProfileImageFilename = userProfileImageFilename;
    }
}