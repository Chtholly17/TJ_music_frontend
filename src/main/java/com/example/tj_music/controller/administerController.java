package com.example.tj_music.controller;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.entity.OriginFrontEnd;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.db.mapper.OriginMapper;
import com.example.tj_music.service.administerService;
import com.example.tj_music.service.originService;
import com.example.tj_music.service.workService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.List;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class administerController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private administerService administerService;
    @Autowired
    private com.example.tj_music.service.workService workService;

    @Autowired
    private com.example.tj_music.service.originService originService;

    @Autowired
    private com.example.tj_music.service.accountService accountService;

    /**
     * 获取所有申诉
     * 用这个API可以获取所有申诉
     * @param
     * @return: list of objects: [appealId, ownerStudentNumber,appealContent]
     */
    @GetMapping("/allAppeals")
    public Result allAppeals() {
        // get all appeals
        List<List<Object>> appeals = administerService.selectAllAppeals();
        return Result.success(appeals);
    }

    /**
     * 通过申诉
     * 输入申诉的ID和申诉者的StudentNumber，通过申诉
     * @param appealId: 需要通过的申诉的ID,之前获取所有申诉的API中可以获取到
     * @param userStudentNumber: 申诉者的学号，之前获取所有申诉的API中可以获取到
     * @return Result(success)
     */
    @PostMapping("/acceptAppeal")
    public Result acceptAppeal(@RequestParam("appealId") int appealId, @RequestParam("userStudentNumber") String userStudentNumber) {
        administerService.acceptAppeal(appealId,userStudentNumber);
        return Result.success();
    }

    /**
     * 拒绝申诉
     * 输入申诉的ID和拒绝理由，拒绝申诉
     * @param appealId: 需要拒绝的申诉的ID,之前获取所有申诉的API中可以获取到
     * @param rejectReason: 拒绝理由
     * @return Result(success)
     */
    @PostMapping("/rejectAppeal")
    public Result rejectAppeal(@RequestParam("appealId") int appealId, @RequestParam("rejectReason") String rejectReason) {
        administerService.rejectAppeal(appealId,rejectReason);
        return Result.success();
    }

    /**
     * 根据id删除指定的作品以及其所有评论
     * 可能的返回值：Result(success) or Result(fail) for work not found
     * @param workId 作品id
     * @return Result(success) or Result(fail) for work not found
     */
    @PostMapping("/deleteWorkAndCommentById")
    public Result deleteWorkAndCommentById(@RequestParam("workId") int workId) {
        boolean result = workService.deleteWorkAndCommentById(workId);
        if(result) {
            return Result.success();
        } else {
            return Result.fail("work not found");
        }
    }

    /**
     * 上传原唱(文件传输有bug)
     * @param originFrontEnd
     * @details:
     * @return
     */
    @PostMapping("/insertOrigin")
    public Result insertOrigin(MultipartHttpServletRequest request) {
        OriginFrontEnd originFrontEnd = new OriginFrontEnd();
        // insert a new origin
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){

            MultipartFile file = request.getFile("originBgmusicFile");
            // print out the file name
            System.out.println(file.getOriginalFilename());

        }else{
            System.out.println("没有文件上传");
        }
        String originId = request.getParameter("originId");
        // convert originId to int
        Integer originIdInt = Integer.parseInt(originId);


        originFrontEnd.setOriginName(request.getParameter("originName"));
        originFrontEnd.setOriginAuthor(request.getParameter("originAuthor"));
        originFrontEnd.setOriginBgmusicFile(request.getFile("originBgmusicFile"));
        originFrontEnd.setOriginVoiceFile(request.getFile("originVoiceFile"));
        originFrontEnd.setOriginPrefaceFile(request.getFile("originPrefaceFile"));
        originFrontEnd.setOriginIntroduction(request.getParameter("originIntroduction"));

        // print out all the information
        System.out.println("we get:");
        System.out.println(originFrontEnd.getOriginName());
        System.out.println(originFrontEnd.getOriginAuthor());
        System.out.println(originFrontEnd.getOriginBgmusicFile().getOriginalFilename());
        System.out.println(originFrontEnd.getOriginVoiceFile().getOriginalFilename());
        System.out.println(originFrontEnd.getOriginPrefaceFile().getOriginalFilename());
        System.out.println(originFrontEnd.getOriginIntroduction());
        System.out.println(originIdInt);
        originService.insertOrigin(originFrontEnd,originIdInt);
        return Result.success();
    }

    /**
     * 通过学号删除用户
     * @param studentNumber 学号
     * @return Result(success) or Result(fail) for user not found
     */
    @PostMapping("/deleteUserByStudentNumber")
    public Result deleteUserByStudentNumber(@RequestParam("studentNumber") String studentNumber) {
        return accountService.deleteUserByStudentNumber(studentNumber);
    }
}
