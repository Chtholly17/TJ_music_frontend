package com.example.tj_music.db.entity;

import java.sql.*;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Work {

    // Declare the fields of the entity class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer workId;
    private Date createTime;
    private String workName;
    private String workContent;
    private Integer workOwner;
    private Integer workOriginVersion;
    private Integer workLike;

    // Create a constructor with no parameters
    public Work() {
    }

    // Create a constructor with parameters
    public Work(Integer workId, Date createTime, String workName, String workContent, Integer workOwner, Integer workOriginVersion, Integer workLike) {
        this.workId = workId;
        this.createTime = createTime;
        this.workName = workName;
        this.workContent = workContent;
        this.workOwner = workOwner;
        this.workOriginVersion = workOriginVersion;
        this.workLike = workLike;
    }

}