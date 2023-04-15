package com.example.tj_music.db.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer post_comment_id;
    private LocalDateTime create_time;
    private Post post_comment_target;
    private String post_comment_owner;
    private String post_comment_content;
    private String post_comment_image_filename;

    public PostComment() {
    }

    public PostComment(Integer post_comment_id, LocalDateTime create_time, Post post_comment_target, String post_comment_owner, String post_comment_content, String post_comment_image_filename) {
        this.post_comment_id = post_comment_id;
        this.create_time = create_time;
        this.post_comment_target = post_comment_target;
        this.post_comment_owner = post_comment_owner;
        this.post_comment_content = post_comment_content;
        this.post_comment_image_filename = post_comment_image_filename;
    }

    public Integer getPost_comment_id() {
        return post_comment_id;
    }

    public void setPost_comment_id(Integer post_comment_id) {
        this.post_comment_id = post_comment_id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public Post getPost_comment_target() {
        return post_comment_target;
    }

    public void setPost_comment_target(Post post_comment_target) {
        this.post_comment_target = post_comment_target;
    }

    public String getPost_comment_owner() {
        return post_comment_owner;
    }

    public void setPost_comment_owner(String post_comment_owner) {
        this.post_comment_owner = post_comment_owner;
    }

    public String getPost_comment_content() {
        return post_comment_content;
    }

    public void setPost_comment_content(String post_comment_content) {
        this.post_comment_content = post_comment_content;
    }

    public String getPost_comment_image_filename() {
        return post_comment_image_filename;
    }

    public void setPost_comment_image_filename(String post_comment_image_filename) {
        this.post_comment_image_filename = post_comment_image_filename;
    }
}