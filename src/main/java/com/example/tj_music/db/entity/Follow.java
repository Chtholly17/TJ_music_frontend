package com.example.tj_music.db.entity;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer followOwner;
    private Integer followTarget;
    public Follow() {
    }

    public Follow(Integer id, Integer followOwner, Integer followTarget) {
        this.id = id;
        this.followOwner = followOwner;
        this.followTarget = followTarget;
    }
}
