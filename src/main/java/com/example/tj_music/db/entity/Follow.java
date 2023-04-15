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
    private User follow_owner;
    private User follow_target;

    public Follow() {
    }

    public Follow(Integer id, User follow_owner, User follow_target) {
        this.id = id;
        this.follow_owner = follow_owner;
        this.follow_target = follow_target;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFollow_owner() {
        return follow_owner;
    }

    public void setFollow_owner(User follow_owner) {
        this.follow_owner = follow_owner;
    }

    public User getFollow_target() {
        return follow_target;
    }

    public void setFollow_target(User follow_target) {
        this.follow_target = follow_target;
    }
}
