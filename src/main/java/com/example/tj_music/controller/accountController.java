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

    /* login
    @param userNumber
    @param password
    @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password) {
        return accountService.loginCheck(userNumber, password);
    }

    /* register send verification code
    @param userNumber
    @param password
    @return Result
     */
    @PostMapping("/register")
    public Result registerSendVerificationCode(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password) throws MessagingException {
        return accountService.registerSendVerificationCode(userNumber);
    }

    /* register check verification code
    @param userNumber
    @param password
    @param verificationCode
    @return Result
     */
    @PostMapping("/registerCheck")
    public Result registerCheckVerificationCode(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password, @RequestParam("verificationCode") String verificationCode) {
        return accountService.registerCheckVerificationCode(userNumber, password, verificationCode);
    }

    /* forget password, send verification code
    @param userNumber
    @return Result
     */
    @PostMapping("/forgetPassword")
    public Result forgetPasswordSendVerificationCode(@RequestParam("userNumber") String userNumber) throws MessagingException {
        return accountService.forgetPasswordSendVerificationCode(userNumber);
    }

    /* forget password, check verification code
    @param userNumber
    @param verificationCode
    @return Result
     */
    @PostMapping("/forgetPasswordCheck")
    public Result forgetPasswordCheckVerificationCode(@RequestParam("userNumber") String userNumber, @RequestParam("verificationCode") String verificationCode) {
        return accountService.forgetPasswordCheckVerificationCode(userNumber, verificationCode);
    }

    /* update password
    @param userNumber
    @param password
    @return Result
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestParam("userNumber") String userNumber, @RequestParam("password") String password) {
        return accountService.updatePassword(userNumber, password);
    }

    /* appeal for account
    @param userNumber
    @param appealContent
    @return Result
     */
    @PostMapping("/appeal")
    public Result appealForAccount(@RequestParam("userNumber") String userNumber, @RequestParam("appealContent") String appealContent) {
        return accountService.appealAccount(userNumber, appealContent);
    }
}