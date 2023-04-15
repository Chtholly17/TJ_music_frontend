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
    private int workId;
    private Timestamp createTime;
    private String workName;
    private String workComment;
    private int workOwner;
    private String workOriginVersion;

    // Create a constructor with parameters
    public Work(int workId, Timestamp createTime, String workName, String workComment, int workOwner, String workOriginVersion) {
        this.workId = workId;
        this.createTime = createTime;
        this.workName = workName;
        this.workComment = workComment;
        this.workOwner = workOwner;
        this.workOriginVersion = workOriginVersion;
    }

}