package com.example.tj_music.controller;

import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.service.accountService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class accountController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private accountService accountService;

    @Autowired
    private UserMapper userMapper;

    /**
     * select user by user id
     * @param userId
     * @return
     */
    @GetMapping("/selectUserById")
    public Result selectUserById(@RequestParam("userId") Integer userId) {
        return Result.success(userMapper.selectUserById(userId));
    }

    /**
     * get all users
     * code:1 represents getting all users succeeded.
     * @return Result
     */
    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return accountService.getAllUsers();
    }

    /**
     * login
     * code:0 represents user does not exist.
     * code:1 represents login succeeded.
     * code:2 represents login failed. The password is incorrect.
     * code:3 represents login failed. The user is banned.
     * @param userNumber
     * @param password
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password) {
        return accountService.loginCheck(userNumber, password);
    }

    /**
     * logout
     * code:2 represents logout failed. The account does not exist.
     * code:1 represents logout succeeded.
     * code:0 represents logout failed.
     * logout
     * @param userNumber
     * @return Result
     */
    @PostMapping("/logout")
    public Result logout(@RequestParam("userNumber") String userNumber) {
//        return accountService.logout(userNumber);
        return Result.success();
    }

    /**
     * register send verification code.
     * code:1 represents sending verification code succeeded.
     * code:0 represents register failed. The account has been existed.
     * @param userNumber
     * @return Result
     */
    @PostMapping("/register")
    public Result registerSendVerificationCode(@RequestParam("userNumber") String userNumber) throws MessagingException {
        return accountService.registerSendVerificationCode(userNumber);
    }

    /**
     * register check verification code.
     * code:2 represents register failed. The password is not the same.
     * code:1 represents register succeeded.
     * code:0 represents register failed. The verification code is wrong.
     *
     * @param userNumber
     * @param password
     * @param checkPassword
     * @param verificationCode
     * @return Result
     */
    @PostMapping("/registerCheck")
    public Result registerCheckVerificationCode(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password, @RequestParam("verificationCode") String verificationCode, @RequestParam("checkPassword") String checkPassword) {
        return accountService.registerCheckVerificationCode(userNumber, password, verificationCode, checkPassword);
    }



    /**
     * forget password send verification code.
     * code:1 represents sending verification code successfully.
     * code:0 represents sending password failed. The account does not exist.
     * @param userNumber
     * @return Result
     */
    @PostMapping("/forgetPassword")
    public Result forgetPasswordSendVerificationCode(@RequestParam("userNumber") String userNumber) throws MessagingException {
        return accountService.forgetPasswordSendVerificationCode(userNumber);
    }

    /**
     * forget password check verification code.
     * code:3 represents checking verification code failed. The account does not exist.
     * code:2 represents checking verification code failed. The password is not the same.
     * code:1 represents checking verification code successfully.
     * code:0 represents the verification code is wrong.
     * @param userNumber
     * @param verificationCode
     * @param password
     * @param checkPassword
     * @return Result
     */
    @PostMapping("/forgetPasswordCheck")
    public Result forgetPasswordCheckVerificationCode(@RequestParam("userNumber") String userNumber, @RequestParam("verificationCode") String verificationCode, @RequestParam("password") String password, @RequestParam("checkPassword") String checkPassword) {
        return accountService.forgetPasswordCheckVerificationCode(userNumber, verificationCode, password, checkPassword);
    }

    /**
     * update password.
     * code:2 represents updating password failed. The account is banned.
     * code:1 represents updating password successfully.
     * code:0 represents updating password failed. The account does not exist.
     * @param userNumber
     * @param password
     * @return Result
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password) {
        return accountService.updatePassword(userNumber, password);
    }

    /**
     * appeal account.
     * code:3 represents appealing account failed. The account does not exist.
     * code:2 represents appealing account succeeded.
     * code:1 represents appealing account failed. The account has been appealed.
     * code:0 represents appealing account failed. The account is valid.
     * @param userNumber
     * @param appealContent
     * @return Result
     */
    @PostMapping("/appeal")
    public Result appealForAccount(@RequestParam("userNumber") String userNumber, @RequestParam("appealContent") String appealContent) {
        return accountService.appealAccount(userNumber, appealContent);
    }

    /**
     * update user status by user id.
     * code:1 represents updating user status successfully.
     * code:0 represents updating user status failed. The account does not exist.
     * @param userId
     * @param status
     * @return Result
     */
    @PostMapping("/updateUserStatus")
    public Result updateUserStatus(@RequestParam("userId") Integer userId, @RequestParam("status") String status) {
        return accountService.updateUserStatusByUserId(userId, status);
    }
}