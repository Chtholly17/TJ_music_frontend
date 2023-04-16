package com.example.tj_music.controller;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.administerService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class administerController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private administerService administerService;

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

}
