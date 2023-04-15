package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Work;
import com.example.tj_music.service.WorkService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface WorkMapper {
    @Select("select * from work where work_id = #{work_id}")
    public Work getWorkById(int work_id);



}
