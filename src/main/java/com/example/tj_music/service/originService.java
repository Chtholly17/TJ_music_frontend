package com.example.tj_music.service;

import com.example.tj_music.db.entity.Image;
import com.example.tj_music.db.entity.Origin;
import com.example.tj_music.db.entity.OriginFrontEnd;
import com.example.tj_music.db.entity.Work;
import com.example.tj_music.db.mapper.OriginMapper;

import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.db.mapper.WorkCommentMapper;
import com.example.tj_music.db.mapper.WorkMapper;
import com.example.tj_music.utils.ImageUtils;
import com.example.tj_music.utils.LrcUtils;
import com.example.tj_music.utils.MusicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.round;

@Service
public class originService {
    @Autowired
    private OriginMapper originMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private WorkCommentMapper workCommentMapper;

    @Autowired
    private MusicUtils musicUtils;
    @Autowired
    private ImageUtils imageUtils;

    @Autowired
    private LrcUtils lrcUtils;

    /**
     * search origin by key word.
     * code:0 represents origin does not exist.
     * code:1 represents search succeeded.
     * @param originTag
     * @return Result
     */
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
    public Result searchOriginByWorkId(Integer workId) {
        Origin origin = originMapper.selectOriginByWorkId(workId);
        if (origin == null) {
            return Result.fail("origin does not exist.");
        }
        return Result.success(origin);
    }

    /**
     * get origin by origin id.
     * code:0 represents origin does not exist.
     * code:1 represents search succeeded.
     * @param originId
     * @return Result
     */
    public Result getOriginByOriginId(Integer originId) {
        Origin origin = originMapper.selectOriginByOriginId(originId);
        if (origin == null) {
            return Result.fail("origin does not exist.");
        }
        return Result.success(origin);
    }

    public Result getOriginList() {
        List<Origin> originList = originMapper.getAllOrigin();
        if (originList.isEmpty()) {
            return Result.fail("origin does not exist.");
        }
        return Result.success(originList);
    }
    public void insertOrigin(OriginFrontEnd originFrontEnd, Integer originId) {



        //  copy non-file attributes from originFrontEnd to origin
        Origin origin = new Origin();
        origin.setOriginName(originFrontEnd.getOriginName());
        origin.setOriginAuthor(originFrontEnd.getOriginAuthor());
        origin.setOriginDuration(10);
        origin.setOriginIntroduction(originFrontEnd.getOriginIntroduction());

//         save the file to the server
//         MusicUtils musicUtils = new MusicUtils();
        EnumMap<MusicUtils.UploadResult,Object> ret;
        // insert bgm
        try {
             ret = musicUtils.upload(originFrontEnd.getOriginBgmusicFile(), "admin", origin.getOriginName()+"_bgmusic");
             origin.setOriginBgmusicFilename(ret.get(MusicUtils.UploadResult.URL).toString());
         } catch (Exception e) {
             e.printStackTrace();
             return;
         }
        // insert voice
         try {
             ret = musicUtils.upload(originFrontEnd.getOriginVoiceFile(), "admin", origin.getOriginName()+"_voice");
             origin.setOriginVoiceFilename(ret.get(MusicUtils.UploadResult.URL).toString());
         } catch (Exception e) {
             e.printStackTrace();
             return;
         }
//         ImageUtils imageUtils = new ImageUtils();
         try {
             String prefaceFilename = imageUtils.upload(originFrontEnd.getOriginPrefaceFile(), "admin", origin.getOriginName()+"_preface");
             origin.setOriginPrefaceFilename(prefaceFilename);
         } catch (Exception e) {
             e.printStackTrace();

             return;
         }
         // insert lrc
        try {
            EnumMap<LrcUtils.UploadResult,Object> lrc_ret;
            lrc_ret = lrcUtils.upload(originFrontEnd.getOriginLrcFile(), "admin", origin.getOriginName()+"_lrc");
            origin.setOriginLrcFilename(lrc_ret.get(LrcUtils.UploadResult.URL).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }



         // get the duration
        int duration = round(musicUtils.getMp3Duration(ret.get(MusicUtils.UploadResult.PATH).toString()));
        origin.setOriginDuration(duration);
        // print the duration
        System.out.println(origin.getOriginDuration());

        // print the filenames
//        System.out.println(origin.getOriginBgmusicFilename());
//        System.out.println(origin.getOriginVoiceFilename());
//        System.out.println(origin.getOriginPrefaceFilename());
        if(originId < 0) {
            originMapper.insertOrigin(origin.getOriginName(), origin.getOriginAuthor(), origin.getOriginBgmusicFilename(), origin.getOriginVoiceFilename(), origin.getOriginDuration(), origin.getOriginPrefaceFilename(), origin.getOriginIntroduction());
            return;
        }
        originMapper.updateOriginById(originId, origin.getOriginName(), origin.getOriginAuthor(), origin.getOriginBgmusicFilename(), origin.getOriginVoiceFilename(), origin.getOriginDuration(), origin.getOriginPrefaceFilename(), origin.getOriginIntroduction());
    }

    public Result deleteOrigin(Integer originId) {
        Origin origin = originMapper.selectOriginByOriginId(originId);
        if (origin == null) {
            return Result.fail("origin does not exist.");
        }
        // select all the works of the origin
        List<Work> workIdList = workMapper.selectWorkByOriginId(originId);
        // traverse the work list
        for (Work work : workIdList) {
            // delete the work_comment of the work
            workCommentMapper.deleteWorkCommentByTarget(work.getWorkId());
            // delete the work
            workMapper.deleteWorkById(work.getWorkId());
        }
        // delete the origin
        originMapper.deleteOriginById(originId);
        return Result.success("delete origin successfully.");
    }
}
