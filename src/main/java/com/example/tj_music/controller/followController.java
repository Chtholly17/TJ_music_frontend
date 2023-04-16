package com.example.tj_music.controller;

import com.example.tj_music.db.entity.Follow;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.followService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class followController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private followService followService;


    /**
     * 关注某人
     * 通过调用这个API来关注某人 code 0:成功 code 1:用户不存在 code 2:关注已存在
     * @param user_student_number 当前发起关注用户的学号
     * @param target_student_number 被关注的用户的学号
     * @return Result 成功返回success，失败返回fail,可能出现的message有： user or target does not exist
     */
    @PostMapping("/follow")
    public Result follow(@NotNull String user_student_number,@NotNull String target_student_number) {

        // this api should be called after login so the user does exist
        User user = followService.selectUserByStudentNumber(user_student_number);
        User target = followService.selectUserByStudentNumber(target_student_number);
        if (user == null || target == null) {
            return new Result(1, "user or target does not exist", null);
        }
        // check whether the follow already exists
        Follow follow = followService.selectFollowByOwnerAndTarget(user.getUserId(), target.getUserId());
        if (follow != null) {
            return new Result(2, "follow already exists", null);
        }
        followService.insertFollow(user.getUserId(), target.getUserId());
        return new Result(0, "success", null);
    }


    /**
     * 取消关注某人
     * 通过这个api来取消关注某人 code: 0:成功 code: 1:用户不存在 code: 2:关注不存在
     * @param user_student_number 当前发起取消关注用户的学号
     * @param target_student_number 被取消关注的用户的学号
     * @return Result 成功返回success，失败返回fail,可能出现的message有： user or target does not exist/follow does not exist
     */
    @DeleteMapping("/unfollow")
    public Result unfollow(@NotNull String user_student_number, @NotNull String target_student_number) {
        // this api should be called after login so the user does exist
        User user = followService.selectUserByStudentNumber(user_student_number);
        User target = followService.selectUserByStudentNumber(target_student_number);
        if (user == null || target == null) {
            return new Result(1, "user or target does not exist", null);
        }
        Follow follow = followService.selectFollowByOwnerAndTarget(user.getUserId(), target.getUserId());
        if (follow == null) {
            return new Result(2, "follow does not exist", null);
        }
        followService.deleteFollow(follow.getId());
        return new Result(0, "success", null);
    }


    /**
     * 查看某人的关注列表
     * 通过这个api来查看某人的关注列表,成功返回的是一个list，list中的每一个元素是一个user code: 0:成功 code: 1:用户不存在
     * @param user_student_number 当前发起查看关注列表用户的学号
     * @return Result 成功返回success，失败返回fail,可能出现的message有： user does not exist
     */
    @GetMapping("/display_user_following")
    public Result displayUserFollowing(@NotNull String user_student_number) {
        // this api should be called after login so the user does exist
        User user = followService.selectUserByStudentNumber(user_student_number);
        if(user == null) {
            return new Result(1, "user does not exist", null);
        }
        List<User> followList = followService.selectFollowByOwner(user.getUserId());
        return new Result(0, "success", followList);
    }

    /**
     * 查看某人的粉丝列表 code 0:成功 code 1:用户不存在
     * 通过这个api来查看某人的粉丝列表,成功返回的是一个list，list中的每一个元素是一个user
     * @param user_student_number 当前发起查看粉丝列表用户的学号
     * @return Result 成功返回success，失败返回fail,可能出现的message有： user does not exist
     */
    @GetMapping("/display_user_follower")
    public Result displayUserFollower(@NotNull String user_student_number) {
        // this api should be called after login so the user does exist
        User user = followService.selectUserByStudentNumber(user_student_number);
        if(user == null) {
            return new Result(1, "user does not exist", null);
        }
        List<User> followList = followService.selectFollowByTarget(user.getUserId());
        return new Result(0, "success", followList);
    }

    /**
     * 查看是否已经关注某人
     * 通过这个api来查看是否已经关注某人 code 0:未关注 code 1:已关注 code 2:用户不存在
     * @param user_student_number 当前发起查看是否已经关注某人用户的学号
     * @param target_student_number 查看是否已经关注的用户的学号
     * @return Result 成功返回success，失败返回fail,可能出现的message有： user or target does not exist/follow does not exist
     */
    @GetMapping("/check_follow")
    public Result checkFollow(@NotNull String user_student_number, @NotNull String target_student_number) {
        // this api should be called after login so the user does exist
        User user = followService.selectUserByStudentNumber(user_student_number);
        User target = followService.selectUserByStudentNumber(target_student_number);
        if (user == null || target == null) {
            return new Result(2, "user or target does not exist", null);
        }
        Follow follow = followService.selectFollowByOwnerAndTarget(user.getUserId(), target.getUserId());
        if (follow == null) {
            return new Result(0, "not follow", null);
        }
        return new Result(1, "follow", null);
    }
}