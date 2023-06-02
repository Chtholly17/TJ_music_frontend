package com.example.tj_music.service;

import com.example.tj_music.VO.GetWorkListVO;
import com.example.tj_music.db.entity.Origin;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.db.mapper.OriginMapper;
import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.db.mapper.WorkCommentMapper;
import com.example.tj_music.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tj_music.db.mapper.WorkMapper;
import com.example.tj_music.db.entity.Work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class workService {
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkCommentMapper workCommentMapper;

    @Autowired
    private OriginMapper originMapper;


    public Result selectWorkByOriginId(int originId) {
        List<Work> works = workMapper.selectWorkByOriginId(originId);
        // init a list of map
        List<Map<String, Object>> list = new ArrayList<>();
        // traverse works
        for(Work work : works) {
            // get user id
            int userId = work.getWorkOwner();
            // get user nickname
            String nickname = userMapper.selectUserById(userId).getUserNickname();
            String userStudentNumber = userMapper.selectUserById(userId).getUserStudentNumber();
            Map<String, Object> map = new HashMap<>();
            map.put("userNickname", nickname);
            map.put("userStudentNumber", userStudentNumber);
            map.put("work", work);

            list.add(map);
        }
        if(works.size() == 0) {
            return Result.fail("no work with origin id " + originId);
        }
        return Result.success(list);
    }

    public Result getNWorks(int n) {
        List<Work> works = workMapper.selectNWorksWithHighestNWorkLike(n);
        if(works == null) {
            return Result.fail("there are no n works");
        }
        return Result.success(works);
    }

    public Result getWorkList(String tag, String order) {
        // print out the tag and order
//        System.out.println("tag: " + tag + " order: " + order);
        List<Work> works = null;
        List<GetWorkListVO> works_vo = new ArrayList<>();
        if (order.equals("like")) {
            works = workMapper.selectWorkByTagWorkLikeDesc(tag);
        } else if (order.equals("comment")) {
            works = workMapper.selectWorkByTagCommentNumberDesc(tag);
        } else {
            works = workMapper.selectWorkByTagUserFansDesc(tag);
        }
        if(works == null) {
            return Result.fail("no work with tag " + tag);
        }

        // print out the works
//        System.out.println("works: " + works);

        // iterate works
        for(Work work : works) {
            GetWorkListVO vo = new GetWorkListVO();
            User owner = userMapper.selectUserById(work.getWorkOwner());
            Origin origin = originMapper.selectOriginByOriginId(work.getWorkOriginVersion());
            vo.setWorkId(work.getWorkId());
            vo.setWorkName(work.getWorkName());
            vo.setWorkAuthorNickname(owner.getUserNickname());
            vo.setScore(work.getWorkPreciseScore());
            vo.setLike(work.getWorkLike());
            vo.setWorkAuthorFans(owner.getUserFansCnt());
            vo.setWorkCommentCnt(workMapper.getWorkCommentCntById(work.getWorkId()));
            vo.setWorkPrefaceFilename(work.getWorkPrefaceFilename());

            works_vo.add(vo);
        }

        return Result.success(works_vo);
    }

    // delete the work and corresponding comments
    public boolean deleteWorkAndCommentById(int workId) {
        // check if the work exists

        if(workCommentMapper.selectWorkCommentByTarget(workId) == null) {
            return false;
        }
        // delete the work
        workCommentMapper.deleteWorkCommentById(workId);
        // delete the comments
        workMapper.deleteWorkAndCommentById(workId);

        return true;
    }

    public Result getWorkById(int workId) {
        Work work = workMapper.selectWorkByWorkId(workId);
        if(work == null) {
            return Result.fail("no work with id " + workId);
        }
        return Result.success(work);
    }

    /**
     * insert work
     * @return
     */
    public Result insertWork(String work_name, String work_comment, String user_student_number, Integer work_origin_version, Integer work_like, String work_voice_filename, String work_tag, String work_preface_filename, Integer work_quality_score, Integer work_precise_score, Integer work_pitch_score) {
        // construct a temp work
        Work work = new Work();
        // use the student number to find the user id
        User user = userMapper.selectUserByStudentNumber(user_student_number);
        if(user == null) {
            return Result.fail("user not found with student number " + user_student_number);
        }

        Origin origin=originMapper.selectOriginByOriginId(work_origin_version);
        if(origin==null){
            return Result.fail("origin not found with origin id " + work_origin_version);
        }


        // fill the work
        work.setWorkName(work_name);
        work.setWorkComment(work_comment);
        work.setWorkOwner(user.getUserId());
        work.setWorkOriginVersion(work_origin_version);
        work.setWorkLike(work_like);
        work.setWorkVoiceFilename(work_voice_filename);
        work.setWorkTag(work_tag);
        work.setWorkPrefaceFilename(work_preface_filename);
        work.setWorkQualityScore(work_quality_score);
        work.setWorkPreciseScore(work_precise_score);
        work.setWorkPitchScore(work_pitch_score);
        work.setWorkTag(origin.getOriginTag());


        // print out all the attributes
        System.out.println(work.toString());

        workMapper.insertWork(work.getWorkName(), work.getWorkComment(), work.getWorkOwner(), work.getWorkOriginVersion(), work.getWorkLike(), work.getWorkVoiceFilename(), work.getWorkTag(), work.getWorkPrefaceFilename(), work.getWorkQualityScore(), work.getWorkPreciseScore(), work.getWorkPitchScore());

        return Result.success(work);
    }

    // add like to work
    public Result addLikeToWork(int workId) {
        Work work = workMapper.selectWorkByWorkId(workId);
        if(work == null) {
            return Result.fail("no work with id " + workId);
        }
        workMapper.addLikeToWork(workId);
        return Result.success();
    }
}
