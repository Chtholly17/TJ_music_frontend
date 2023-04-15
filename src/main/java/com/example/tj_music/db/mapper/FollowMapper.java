package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper {
    // get follow by id
    @Select("SELECT * FROM follow")
    public List<Follow> getAll();
}
