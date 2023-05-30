package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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



    //按work的评论数量降序排列
    @Select("SELECT * FROM work LEFT JOIN " +
            "(SELECT work_comment_id, COUNT(work_comment_id) AS work_comment_number " +
            "FROM work_comment " +
            "GROUP BY work_comment_id) AS wc " +
            "ON work.work_id = wc.work_comment_id " +
            "WHERE work_tag LIKE CONCAT('%', #{tag}, '%')" +
            "ORDER BY wc.work_comment_number DESC")
    public List<Work> selectWorkByTagCommentNumberDesc(String tag);

    //按work的作者的粉丝数降序排列
    @Select("SELECT * FROM work LEFT JOIN " +
            "(SELECT user_id, user_fans_cnt " +
            "FROM user) AS usr " +
            "ON work.work_owner = usr.user_id " +
            "WHERE work_tag LIKE CONCAT('%', #{tag}, '%')" +
            "ORDER BY usr.user_fans_cnt DESC")
    public List<Work> selectWorkByTagUserFansDesc(String tag);

    @Select("SELECT * FROM work WHERE work_tag LIKE CONCAT('%', #{tag}, '%')" +
            "ORDER BY work_like DESC")
    public List<Work> selectWorkByTagWorkLikeDesc(String tag);

    // remove all the work of given id
    @Delete("delete from work where work_id = #{workId}")
        public void deleteWorkAndCommentById(Integer workId);

    // get the work comment cnt by work id
    @Select("select count(*) from work_comment where work_comment_id = #{workId}")
    public Integer getWorkCommentCntById(Integer workId);

    // get the works by work_origin_version
    @Select("select * from work where work_origin_version = #{originId}")
    public List<Work> getWorksByOriginId(Integer originId);

    // delete work by work_id
    @Delete("delete from work where work_id = #{workId}")
    public void deleteWorkById(Integer workId);

    // insert new work
    @Insert("insert into work (work_name, work_comment, work_owner, work_origin_version, work_like, work_voice_filename, work_tag, work_preface_filename, work_quality_score, work_precise_score, work_pitch_score) values (#{workId}, #{createTime}, #{workName}, #{workComment}, #{workOwner}, #{workOriginVersion}, #{workLike}, #{workVoiceFilename}, #{workTag}, #{workPrefaceFilename}, #{workQualityScore}, #{workPreciseScore}, #{workPitchScore})")
    public void insertWork(String work_name, String work_comment, Integer work_owner, Integer work_origin_version, Integer work_like, String work_voice_filename, String work_tag, String work_preface_filename, Integer work_quality_score, Integer work_precise_score, Integer work_pitch_score);

}
