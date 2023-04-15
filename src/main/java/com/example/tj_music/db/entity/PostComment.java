package com.example.tj_music.db.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postCommentId;
    private Date createTime;
    private Integer postCommentTarget;
    private String postCommentOwner;
    private String postCommentContent;

    public PostComment() {
    }

    public PostComment(Integer postCommentId, Date createTime, Integer postCommentTarget, String postCommentOwner, String postCommentContent) {
        this.postCommentId = postCommentId;
        this.createTime = createTime;
        this.postCommentTarget = postCommentTarget;
        this.postCommentOwner = postCommentOwner;
        this.postCommentContent = postCommentContent;
    }
}