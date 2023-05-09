package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.tj_music.db.entity.Work;

import java.util.List;

@Mapper
public interface WorkMapper {
    // get all work
    @Select("select * from work")
    public List<Work> getAllWork();

    // select work by owner Id
    @Select("select * from work where work_owner = #{ownerId}")
    public List<Work> selectWorkByOwnerId(Integer ownerId);

    // select work by origin Id
    @Select("select * from work where work_origin_version = #{originId}")
    public List<Work> selectWorkByOriginId(Integer originId);

    // select work by work_id
    @Select("select * from work where work_id = #{workId}")
    public Work selectWorkByWorkId(Integer workId);

    // select n works with highest n work_like value
    @Select("select * from work order by work_like desc limit #{n}")
    public List<Work> selectNWorksWithHighestNWorkLike(Integer n);

    @Select("SELECT * FROM work WHERE work_tag LIKE CONCAT('%', #{tag}, '%')")
    public List<Work> selectWorkByTag(String tag);
}
