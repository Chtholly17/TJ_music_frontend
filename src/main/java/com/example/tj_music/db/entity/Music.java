package com.example.tj_music.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    MultipartFile file;
    Integer originId;
    String userStudentNumber;
}
