package com.example.tj_music.service;

import com.example.tj_music.db.entity.Work;
import com.example.tj_music.db.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {
    @Autowired
    private WorkMapper workMapper;
    // get all work

    public List<Work> getAll(){

        return workMapper.getAllWork();
    }
}
