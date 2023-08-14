package com.example.tj_music.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@Table(name = "work", schema = "tjse")
public class Work {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "workId", nullable = false)
    private int workId;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;
    @Basic
    @Column(name = "workName", nullable = false, length = 255)
    private String workName;
    @Basic
    @Column(name = "workComment", nullable = false, length = 255)
    private String workComment;
    @Basic
    @Column(name = "workOwner", nullable = false)
    private int workOwner;
    @Basic
    @Column(name = "workOriginVersion", nullable = false)
    private int workOriginVersion;
    @Basic
    @Column(name = "work_like", nullable = true)
    private Integer workLike;
    @Basic
    @Column(name = "work_voice_filename", nullable = false, length = 255)
    private String workVoiceFilename;
    @Basic
    @Column(name = "work_tag", nullable = false, length = 100)
    private String workTag;
    @Basic
    @Column(name = "work_preface_filename", nullable = false, length = 255)
    private String workPrefaceFilename;
    @Basic
    @Column(name = "workQualityScore", nullable = false)
    private int workQualityScore;
    @Basic
    @Column(name = "workPreciseScore", nullable = false)
    private int workPreciseScore;
    @Basic
    @Column(name = "workPitchScore", nullable = false)
    private int workPitchScore;

    @Basic
    @Column(name = "work_comment_cnt", nullable = false)
    private int workCommentCnt;

    @Basic
    @Column(name = "work_owner_fans_cnt", nullable = false)
    private int workOwnerFansCnt;

}

