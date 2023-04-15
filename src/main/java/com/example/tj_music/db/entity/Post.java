package com.example.tj_music.db.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer post_id;
    private LocalDateTime create_time;
    private User post_owner;
    private String post_name;
    private String post_content;
    private Integer post_like;

    public Post() {
    }

    public Post(Integer post_id, LocalDateTime create_time, User post_owner, String post_name, String post_content, Integer post_like) {
        this.post_id = post_id;
        this.create_time = create_time;
        this.post_owner = post_owner;
        this.post_name = post_name;
        this.post_content = post_content;
        this.post_like = post_like;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public User getPost_owner() {
        return post_owner;
    }

    public void setPost_owner(User post_owner) {
        this.post_owner = post_owner;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public Integer getPost_like() {
        return post_like;
    }

    public void setPost_like(Integer post_like) {
        this.post_like = post_like;
    }
}