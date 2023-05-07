package com.example.tj_music.service;

import com.example.tj_music.db.entity.Origin;
import com.example.tj_music.db.mapper.OriginMapper;

import com.example.tj_music.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
@Service
public class originService {
    @Autowired
    private OriginMapper originMapper;

    /**
     * search origin by key word.
     * code:0 represents origin does not exist.
     * code:1 represents search succeeded.
     * @param originTag
     * @return Result
     */
    @PostMapping("/searchOrigin")
    public Result searchOriginByTag(String originTag) {
        List<Origin> originList =  originMapper.selectOriginByTag(originTag);
        if (originList.isEmpty()) {
            return Result.fail("origin does not exist.");
        }
        return Result.success(originList);
    }

    /**
     * search origin by work id.
     * code:0 represents origin does not exist.
     * code:1 represents search succeeded.
     * @param workId
     * @return Result
     */
    @PostMapping("/searchOriginByWorkId")
    public Result searchOriginByWorkId(Integer workId) {
        Origin origin = originMapper.selectOriginByWorkId(workId);
        if (origin == null) {
            return Result.fail("origin does not exist.");
        }
        return Result.success(origin);
    }
}
