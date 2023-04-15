package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Appeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppealMapper {
    // get all appeal
    @Select("select * from appeal")
    public List<Appeal> getAllAppeal();
}
