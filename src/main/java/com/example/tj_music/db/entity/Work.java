package com.example.tj_music.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "work", schema = "tjse")
public class Work {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "work_id", nullable = false)
    private int workId;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;
    @Basic
    @Column(name = "work_name", nullable = false, length = 255)
    private String workName;
    @Basic
    @Column(name = "work_comment", nullable = false, length = 255)
    private String workComment;
    @Basic
    @Column(name = "work_owner", nullable = false)
    private int workOwner;
    @Basic
    @Column(name = "work_origin_version", nullable = false)
    private int workOriginVersion;
    @Basic
    @Column(name = "work_like", nullable = true)
    private Integer workLike;
    @Basic
    @Column(name = "work_voice_filename", nullable = false, length = 255)
    private String workVoiceFilename;
    @Basic
    @Column(name = "work_score", nullable = false)
    private int workScore;
    @Basic
    @Column(name = "work_tag", nullable = false, length = 100)
    private String workTag;
    @Basic
    @Column(name = "work_preface_filename", nullable = false, length = 255)
    private String workPrefaceFilename;

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkComment() {
        return workComment;
    }

    public void setWorkComment(String workComment) {
        this.workComment = workComment;
    }

    public int getWorkOwner() {
        return workOwner;
    }

    public void setWorkOwner(int workOwner) {
        this.workOwner = workOwner;
    }

    public int getWorkOriginVersion() {
        return workOriginVersion;
    }

    public void setWorkOriginVersion(int workOriginVersion) {
        this.workOriginVersion = workOriginVersion;
    }

    public Integer getWorkLike() {
        return workLike;
    }

    public void setWorkLike(Integer workLike) {
        this.workLike = workLike;
    }

    public String getWorkVoiceFilename() {
        return workVoiceFilename;
    }

    public void setWorkVoiceFilename(String workVoiceFilename) {
        this.workVoiceFilename = workVoiceFilename;
    }

    public int getWorkScore() {
        return workScore;
    }

    public void setWorkScore(int workScore) {
        this.workScore = workScore;
    }

    public String getWorkTag() {
        return workTag;
    }

    public void setWorkTag(String workTag) {
        this.workTag = workTag;
    }

    public String getWorkPrefaceFilename() {
        return workPrefaceFilename;
    }

    public void setWorkPrefaceFilename(String workPrefaceFilename) {
        this.workPrefaceFilename = workPrefaceFilename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Work that = (Work) o;
        return workId == that.workId && workOwner == that.workOwner && workOriginVersion == that.workOriginVersion && workScore == that.workScore && Objects.equals(createTime, that.createTime) && Objects.equals(workName, that.workName) && Objects.equals(workComment, that.workComment) && Objects.equals(workLike, that.workLike) && Objects.equals(workVoiceFilename, that.workVoiceFilename) && Objects.equals(workTag, that.workTag) && Objects.equals(workPrefaceFilename, that.workPrefaceFilename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workId, createTime, workName, workComment, workOwner, workOriginVersion, workLike, workVoiceFilename, workScore, workTag, workPrefaceFilename);
    }
}

