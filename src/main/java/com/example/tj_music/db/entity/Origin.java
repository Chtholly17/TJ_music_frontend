package com.example.tj_music.db.entity;

import java.sql.*;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Origin {

    // Declare the fields of the entity class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int originId;
    private String originName;
    private String originAuthor;

    private String originBgmusicFilename;

    private String originVoiceFilename;

    // Create a constructor without parameters
    public Origin() {
    }
    // Create a constructor with parameters
    public Origin(int originId, String originName, String originAuthor) {
        this.originId = originId;
        this.originName = originName;
        this.originAuthor = originAuthor;
    }

}