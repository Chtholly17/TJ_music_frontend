package com.example.tj_music.service;

import com.example.tj_music.db.entity.Follow;
import com.example.tj_music.db.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    private FollowMapper followMapper;
    // get follow by id
    public List<Follow> getFollowById(Integer follow_id){
        return followMapper.getAll();
    }

}
