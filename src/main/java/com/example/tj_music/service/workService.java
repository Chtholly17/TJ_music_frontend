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

        // iterate works
        for(Work work : works) {
            GetWorkListVO vo = new GetWorkListVO();
            User owner = userMapper.selectUserById(work.getWorkOwner());
            Origin origin = originMapper.selectOriginByOriginId(work.getWorkOriginVersion());
            vo.setWorkId(work.getWorkId());
            vo.setWorkName(work.getWorkName());
            vo.setWorkAuthorNickname(owner.getUserNickname());
            vo.setScore(work.getWorkScore());
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

}
