package com.example.tj_music.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMessageListVO {
    String senderStudentNumber;
    String message_content;
    LocalDateTime message_time;
}
