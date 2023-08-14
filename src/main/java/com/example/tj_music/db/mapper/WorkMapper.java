package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.*;
import com.example.tj_music.db.entity.Work;

import java.util.List;

@Mapper
public interface WorkMapper {
    // get all work
    @Select("select * from work")
    public List<Work> getAllWork();

    // select work by owner Id
    @Select("select * from work where workOwner = #{ownerId}")
    public List<Work> selectWorkByOwnerId(Integer ownerId);

    // select work by origin Id
    @Select("select * from work where workOriginVersion = #{originId}")
    public List<Work> selectWorkByOriginId(Integer originId);

    // select work by workId
    @Select("select * from work where workId = #{workId}")
    public Work selectWorkByWorkId(Integer workId);

    // select n works with highest n work_like value
    @Select("select * from work order by work_like desc limit #{n}")
    public List<Work> selectNWorksWithHighestNWorkLike(Integer n);



    //按work的评论数量降序排列
    @Select("SELECT * FROM work WHERE work_tag LIKE CONCAT('%', #{tag}, '%')" +
            "ORDER BY work_comment_cnt DESC,work_like DESC,work_owner_fans_cnt DESC")
    public List<Work> selectWorkByTagCommentNumberDesc(String tag);

    //按work的作者的粉丝数降序排列
    @Select("SELECT * FROM work WHERE work_tag LIKE CONCAT('%', #{tag}, '%')" +
            "ORDER BY work_owner_fans_cnt DESC,work_like DESC,work_comment_cnt DESC")
    public List<Work> selectWorkByTagUserFansDesc(String tag);

    @Select("SELECT * FROM work WHERE work_tag LIKE CONCAT('%', #{tag}, '%')" +
            "ORDER BY work_like DESC,work_comment_cnt DESC,work_owner_fans_cnt DESC")
    public List<Work> selectWorkByTagWorkLikeDesc(String tag);

    // remove all the work of given id
    @Delete("delete from work where workId = #{workId}")
        public void deleteWorkAndCommentById(Integer workId);

    // get the work comment cnt by work id
    @Select("select count(*) from workComment where work_comment_target = #{workId}")
    public Integer getWorkCommentCntById(Integer workId);

    // get the works by workOriginVersion
    @Select("select * from work where workOriginVersion = #{originId}")
    public List<Work> getWorksByOriginId(Integer originId);

    // delete work by workId
    @Delete("delete from work where workId = #{workId}")
    public void deleteWorkById(Integer workId);

    // insert new work
    @Insert("insert into work (workName, workComment, workOwner, workOriginVersion, " +
            "work_like, work_voice_filename, work_tag, work_preface_filename," +
            " workQualityScore, workPreciseScore, workPitchScore)" +
            " values (#{workName}, #{workComment}, #{workOwner}, " +
            "#{workOriginVersion}, #{workLike}, #{workVoiceFilename}, " +
            "#{workTag}, #{workPrefaceFilename}, #{workQualityScore}, " +
            "#{workPreciseScore}, #{workPitchScore})")
    public void insertWork(String workName, String workComment, Integer workOwner,
                           Integer workOriginVersion, Integer workLike, String workVoiceFilename,
                           String workTag, String workPrefaceFilename, Integer workQualityScore,
                           Integer workPreciseScore, Integer workPitchScore);


    // add like to work
    @Insert("update work set work_like = work_like + 1 where workId = #{workId}")
    public void addLikeToWork(Integer workId);

    // update work comment cnt
    @Update("update work set work_comment_cnt = #{workCommentCnt} where workId = #{workId}")
    public void updateWorkCommentCnt(Integer workId, Integer workCommentCnt);

    // update work owner fans
    @Update("update work set work_owner_fans_cnt = #{workOwnerFans} where workId = #{workId}")
    public void updateWorkOwnerFans(Integer workId, Integer workOwnerFans);

    // get works with given tag
    @Select("select * from work where work_tag like CONCAT('%', #{tag}, '%')")
    public List<Work> getWorksByTag(String tag);
}
