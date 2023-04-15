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

    private String user_student_number;

    private String user_nickname;
    private String user_password;
    private String user_signature;
    private String user_status;
    private String user_profile_image_filename;

}