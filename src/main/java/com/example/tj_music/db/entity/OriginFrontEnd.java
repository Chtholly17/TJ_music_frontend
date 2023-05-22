package com.example.tj_music.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginFrontEnd {
    String originName;
    String originAuthor;
    MultipartFile originBgmusicFile;
    MultipartFile originVoiceFile;
    MultipartFile originPrefaceFile;
    String originIntroduction;
}
