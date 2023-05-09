package com.example.tj_music.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "work_comment", schema = "tjse")
public class WorkComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "work_comment_id", nullable = false)
    private int workCommentId;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;
    @Basic
    @Column(name = "work_comment_target", nullable = false)
    private int workCommentTarget;
    @Basic
    @Column(name = "work_comment_owner", nullable = false)
    private int workCommentOwner;
    @Basic
    @Column(name = "work_comment_content", nullable = false, length = 255)
    private String workCommentContent;

    public int getWorkCommentId() {
        return workCommentId;
    }

    public void setWorkCommentId(int workCommentId) {
        this.workCommentId = workCommentId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getWorkCommentTarget() {
        return workCommentTarget;
    }

    public void setWorkCommentTarget(int workCommentTarget) {
        this.workCommentTarget = workCommentTarget;
    }

    public int getWorkCommentOwner() {
        return workCommentOwner;
    }

    public void setWorkCommentOwner(int workCommentOwner) {
        this.workCommentOwner = workCommentOwner;
    }

    public String getWorkCommentContent() {
        return workCommentContent;
    }

    public void setWorkCommentContent(String workCommentContent) {
        this.workCommentContent = workCommentContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkComment that = (WorkComment) o;
        return workCommentId == that.workCommentId && workCommentTarget == that.workCommentTarget && workCommentOwner == that.workCommentOwner && Objects.equals(createTime, that.createTime) && Objects.equals(workCommentContent, that.workCommentContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workCommentId, createTime, workCommentTarget, workCommentOwner, workCommentContent);
    }
}
