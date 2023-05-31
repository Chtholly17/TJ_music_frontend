package com.example.tj_music.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessageBriefListVO {
    String student_number;
    String nickname;
    String profile_image_filename;
    String last_message_content;

}
