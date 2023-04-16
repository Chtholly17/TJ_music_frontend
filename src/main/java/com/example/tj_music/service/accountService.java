package com.example.tj_music.service;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.mapper.AppealMapper;
import com.example.tj_music.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class accountService {
    @Autowired
    private AppealMapper appealMapper;
    @Autowired
    private UserMapper  userMapper;


}