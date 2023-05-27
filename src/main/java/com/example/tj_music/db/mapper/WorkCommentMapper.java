/*
 * @Description: 
 * @Date: 2023-05-21 21:47:30
 * @LastEditTime: 2023-05-21 22:05:48
 */
package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.WorkComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface WorkCommentMapper {
    // create a new work comment
    @Insert("insert into work_comment(work_comment_target, work_comment_owner, work_comment_content) values(#{workCommentTarget}, #{workCommentOwner}, #{workCommentContent})")
    public void insertWorkComment(Integer workCommentTarget, Integer workCommentOwner, String workCommentContent);

    // select comments by work_comment_target
    @Select("select * from work_comment where work_comment_target = #{workCommentTarget}")
    public List<WorkComment> selectWorkCommentByTarget(Integer workCommentTarget);

    // delete work comment by work_comment_id
    @Select("delete from work_comment where work_comment_target = #{workCommentTarget}")
    public void deleteWorkCommentById(Integer workCommentTarget);

    // delete work comment by work_comment_target
    @Delete("delete from work_comment where work_comment_target = #{workCommentTarget}")
    public void deleteWorkCommentByTarget(Integer workCommentTarget);
}
