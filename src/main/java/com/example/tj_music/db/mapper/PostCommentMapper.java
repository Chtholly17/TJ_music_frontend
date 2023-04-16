package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.PostComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostCommentMapper {
    // get all post comment
    @Select("select * from post_comment")
    public List<PostComment> getAllPostComment();
}
