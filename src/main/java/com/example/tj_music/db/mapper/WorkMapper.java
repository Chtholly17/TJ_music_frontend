package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Work;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkMapper {
    // get all work
    @Select("select * from work")
    public List<Work> getAllWork();
}
