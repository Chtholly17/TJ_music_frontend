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
    @Select("select * from origin where origin_name like CONCAT('%', #{originTag, jdbcType=VARCHAR}, '%')")
    public List<Origin> selectOriginByTag(String originTag);

    // Select origin by work id
    @Select("select * from origin inner join work on origin.origin_id = work.work_origin_version" +
            " where work.work_id = #{workId}")
    public Origin selectOriginByWorkId(Integer workId);

    // Select origin by origin id
    @Select("select * from origin where origin_id = #{originId}")
    public Origin selectOriginByOriginId(Integer originId);
}
