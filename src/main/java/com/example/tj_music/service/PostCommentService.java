package com.example.tj_music.service;

import com.example.tj_music.db.entity.PostComment;
import com.example.tj_music.db.mapper.PostCommentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {

    @Autowired
    private PostCommentMapper postCommentMapper;

    // get all postComment
    public List<PostComment> getAll(){
        return postCommentMapper.getAllPostComment();
    }
}
