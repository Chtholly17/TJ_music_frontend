package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.WorkComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface WorkCommentMapper {
    // create a new work comment
    @Insert("insert into work_comment(work_comment_target, work_comment_owner, work_comment_content) values(#{workCommentTarget}, #{workCommentOwner}, #{workCommentContent})")
    public void createWorkComment(Integer workCommentTarget, Integer workCommentOwner, String workCommentContent);
}
