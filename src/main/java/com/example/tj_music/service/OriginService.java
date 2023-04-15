package com.example.tj_music.service;

import com.example.tj_music.db.mapper.OriginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginService {
    @Autowired
    private OriginMapper originMapper;

    // get all
    public void getAll(){
        originMapper.getAll();
    }
}
