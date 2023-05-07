package com.example.tj_music.controller;

import com.example.tj_music.db.entity.User;
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

    /**
     * login
     * code:0 represents user does not exist.
     * code:1 represents login succeeded.
     * code:2 represents login failed.
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
     * code:1 represents logout succeeded.
     * code:0 represents logout failed.
     * logout
     * @param userNumber
     * @return Result
     */
    @PostMapping("/logout")
    public Result logout(@RequestParam("userNumber") String userNumber) {
        return accountService.logout(userNumber);
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

}