package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper {
    // get all follow
    @Select("select * from follow")
    public List<Follow> getAllFollow();
}
