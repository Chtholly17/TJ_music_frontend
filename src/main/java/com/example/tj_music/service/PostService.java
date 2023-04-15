package com.example.tj_music.service;

import com.example.tj_music.db.entity.Post;
import com.example.tj_music.db.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    // get all post
    public List<Post> getAll(){
        return postMapper.getAllPost();
    }

}
