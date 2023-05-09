package com.example.tj_music.service;

import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.utils.Result;
import com.example.tj_music.db.entity.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tj_music.db.mapper.WorkMapper;

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

    public Result getWorkList(String tag) {
        List<Work> works = workMapper.selectWorkByTag(tag);
        if(works == null) {
            return Result.fail("no work with tag " + tag);
        }
        return Result.success(works);
    }

}
