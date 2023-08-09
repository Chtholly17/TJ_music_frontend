package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.entity.AppealContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppealMapper {

    // select all appeal content, appeal status and user student number
    @Select("select appeal_content, appeal_status, user_student_number from appeal, user where appeal_owner = userId")
    public List<AppealContent> selectAllAppealContentAndStatusAndStudentNumber();

    // select appeal by owner
    @Select("select * from appeal where appeal_owner = #{appealOwner}")
    public List<Appeal> selectAppealByOwner(Integer appealOwner);

    // select appeal_owner by id
    @Select("select appeal_owner from appeal where appeal_id = #{appealId}")
    public List<Integer> selectAppealOwnerById(Integer appealId);

    // insert appeal, with status processing as default
    @Select("insert into appeal(appeal_owner, appeal_content) values( #{appealOwner}, #{appealContent})")
    public void insertAppeal(Integer appealOwner, String appealContent);

    // select all appeal
    @Select("select * from appeal")
    public List<Appeal> selectAllAppeal();

    // delete appeal by id
    @Select("delete from appeal where appeal_id = #{id}")
    public void deleteAppealById(Integer id);

    // delete appeal by appeal owner
    @Select("delete from appeal where appeal_owner = #{appealOwner}")
    public void deleteAppealByAppealOwner(Integer appealOwner);

    // update appeal status by id
    @Select("update appeal set appeal_status = #{appealStatus} where appeal_id = #{id}")
    public void updateAppealStatusById(String appealStatus, Integer id);

    // update appeal reject reason by id
    @Select("update appeal set appeal_reject_reason = #{appealRejectReason} where appeal_id = #{id}")
    public void updateAppealRejectReasonById(String appealRejectReason, Integer id);


}
