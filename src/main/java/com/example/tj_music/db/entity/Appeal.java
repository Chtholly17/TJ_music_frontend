package com.example.tj_music.db.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer appeal_id;
    private LocalDateTime create_time;
    private String appeal_content;

    public Appeal() {
    }

    public Appeal(Integer appeal_id, LocalDateTime create_time, String appeal_content) {
        this.appeal_id = appeal_id;
        this.create_time = create_time;
        this.appeal_content = appeal_content;
    }

    public Integer getAppeal_id() {
        return appeal_id;
    }

    public void setAppeal_id(Integer appeal_id) {
        this.appeal_id = appeal_id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public String getAppeal_content() {
        return appeal_content;
    }

    public void setAppeal_content(String appeal_content) {
        this.appeal_content = appeal_content;
    }
}