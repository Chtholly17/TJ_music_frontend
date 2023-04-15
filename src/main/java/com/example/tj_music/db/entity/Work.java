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
    private Integer work_id;
    private String work_name;
    private String work_content;
    private String work_image_filename;
    private Integer work_like;

}