package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper {

    // insert follow
    @Select("insert into follow(follow_owner, follow_target) values(#{followOwner}, #{followTarget})")
    public void insertFollow(Integer followOwner, Integer followTarget);

    // delete follow by id
    @Select("delete from follow where id = #{id}")
    public void deleteFollowById(Integer id);

    // select follow by owner
    @Select("select * from follow where follow_owner = #{followOwner}")
    public List<Follow> selectFollowByOwner(Integer followOwner);

    // select follow by target
    @Select("select * from follow where follow_target = #{followTarget}")
    public List<Follow> selectFollowByTarget(Integer followTarget);


}
