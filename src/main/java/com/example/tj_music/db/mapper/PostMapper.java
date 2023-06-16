package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    // get all post
    @Select("select * from post")
    public List<Post> getAllPost();
}
