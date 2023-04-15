package com.example.tj_music.db.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer user_id;

    private LocalDateTime create_time;

    private String user_student_number;

    private String user_nickname;
    private String user_password;
    private String user_signature;
    private String user_status;
    private String user_profile_image_filename;

    public User() {
    }

    public User(String user_student_number,String user_nickname,String user_password){
        // get the current time as the created time
        this.create_time = LocalDateTime.now();
        this.user_student_number = user_student_number;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_signature = "这个人很懒，什么都没有留下";
        this.user_status = "online";
        this.user_profile_image_filename = "default.jpg";
    }

    public User(Integer user_id,LocalDateTime create_time,String user_student_number,String user_nickname,String user_password,String user_signature,String user_status,String user_profile_image_filename){
        this.user_id = user_id;
        this.create_time = create_time;
        this.user_student_number = user_student_number;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_signature = user_signature;
        this.user_status = user_status;
        this.user_profile_image_filename = user_profile_image_filename;
    }

    public String toString(){
        return "User";
    }
}