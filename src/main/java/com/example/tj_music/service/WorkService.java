package com.example.tj_music.service;

import com.example.tj_music.db.entity.Work;
import com.example.tj_music.db.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {
    @Autowired
    private WorkMapper workMapper;
    // get work by id
    public Work getWorkById(int work_id){

        return workMapper.getWorkById(work_id);
    }
}
