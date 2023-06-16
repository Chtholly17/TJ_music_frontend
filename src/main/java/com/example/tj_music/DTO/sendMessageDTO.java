package com.example.tj_music.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sendMessageDTO {
    String sender_student_number;
    String receiver_student_number;
    String content;
}
