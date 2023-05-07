package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.tj_music.db.entity.Origin;

import java.util.List;

@Mapper
public interface OriginMapper {
    // Select all origins
    @Select("select * from origin")
    public List<Origin> getAllOrigin();

    // Select origin by tag
    @Select("select * from origin where origin_name like ’%#{originTag}%‘")
    public Origin selectOriginByTag(String originTag);

    // Select origin by work id
    @Select("select * from origin where work_id = #{workId}")
    public Origin selectOriginByWorkId(Integer workId);
}
