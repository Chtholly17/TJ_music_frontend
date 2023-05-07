package com.example.tj_music.service;

import com.example.tj_music.utils.Result;
import com.example.tj_music.db.entity.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tj_music.db.mapper.WorkMapper;

import java.util.List;

@Service
public class workService {
    @Autowired
    private WorkMapper workMapper;

    public Result selectWorkByOriginId(int originId) {
        List<Work> works = workMapper.selectWorkByOriginId(originId);
        if(works == null) {
            return Result.fail("no work with origin id " + originId);
        }
        return Result.success(works);
    }

    public Result getNWorks(int n) {
        List<Work> works = workMapper.selectNWorksWithHighestNWorkLike(n);
        if(works == null) {
            return Result.fail("there are no n works");
        }
        return Result.success(works);
    }

}
