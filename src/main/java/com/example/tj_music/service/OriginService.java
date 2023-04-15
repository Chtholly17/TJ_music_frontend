package com.example.tj_music.service;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.entity.Origin;
import com.example.tj_music.db.mapper.OriginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService {
    @Autowired
    private OriginMapper originMapper;

    // get all origin
    public List<Origin> getAll(){
        return originMapper.getAllOrigin();
    }
}
