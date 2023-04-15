package com.example.tj_music.service;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.mapper.AppealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealService {

    @Autowired
    private AppealMapper appealMapper;

    // get all appeal
    public List<Appeal> getAll(){
        return appealMapper.getAllAppeal();
    }
}
