package com.example.tj_music.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Integer messageId;
    private LocalDateTime createTime;
    private Integer senderId;
    private Integer receiverId;
    private String content;

    // getters and setters

}
