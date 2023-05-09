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
    private String workComment;
    private Integer workOwner;
    private Integer workOriginVersion;
    private Integer workLike;
    private String workVoiceFilename;
    private Integer workScore;
    private String workPrefaceFilename;
}