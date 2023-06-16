package com.example.tj_music.db.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class AppealContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String appealContent;
    private String appealStatus;
    private String userStudentNumber;
}
