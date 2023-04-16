package com.example.tj_music.service;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.db.mapper.AppealMapper;
import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service
public class accountService {
    @Autowired
    private AppealMapper appealMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * login check.
     * code:0 represents user does not exist.
     * code:1 represents login succeeded.
     * code:2 represents login failed.
     * @param userNumber
     * @param password
     * @return Result
     */
    public Result loginCheck(String userNumber, String password) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (user == null)
            return new Result(0, "Login failed. The user does not exist.", null);
        if (Objects.equals(user.getUserPassword(), password))
            return new Result(1, "Login succeeded", null);
        else
            return new Result(2, "Login failed", null);
    }

    /**
     * send verification code.
     * code:1 represents the verification code sent successfully.
     * @param userNumber
     * @return Result
     */
    public Result sendVerificationCode(String userNumber) throws MessagingException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        String VerificationCode = "12345";
        String email = userNumber + "@tongji.edu.cn";
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");//发送邮件协议
        properties.setProperty("mail.smtp.auth", "true");//需要验证
        Session session = Session.getInstance(properties);
        session.setDebug(true);//debug模式
        //邮件信息
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("982017264@qq.com"));//设置发送人
        message.setText("你的验证码为：" + VerificationCode + "。请注意，验证码有效时间为2分钟！！！");//设置邮件内容
        message.setSubject("邮箱验证");//设置邮件主题
        //发送邮件
        Transport tran = session.getTransport();
//        tran.connect("smtp.sina.com", 25, "邮箱账户", "邮箱授权码");//连接到新浪邮箱服务器
        tran.connect("smtp.qq.com", 587, "982017264@qq.com", "napfmggjgflzbcja");//连接到QQ邮箱服务器
        tran.sendMessage(message, new Address[]{new InternetAddress(email)});//设置邮件接收人
        tran.close();
        return new Result(1, "send verification code succeeded", null);
    }

    /**
     * register send verification code.
     * code:1 represents sending verification code succeeded.
     * code:0 represents register failed. The account has been existed.
     * @param userNumber
     * @return Result
     */
    public Result registerSendVerificationCode(String userNumber) throws MessagingException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (user == null) {
            return sendVerificationCode(userNumber);
        } else
            return new Result(0, "Register failed. The account has been existed", null);
    }

    /**
     * register check verification code.
     * code:1 represents register succeeded.
     * code:0 represents register failed. The verification code is wrong.
     * @param userNumber
     * @param password
     * @param verificationCode
     * @return Result
     */
    public Result registerCheckVerificationCode(String userNumber, String password, String verificationCode) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (Objects.equals(verificationCode, "12345")) {
            userMapper.insertUser(userNumber, password, "user", "user", "user");
            return new Result(1, "Register succeeded", null);
        } else
            return new Result(0, "Register failed. The verification code is wrong", null);
    }

    /**
     * forget password send verification code.
     * code:1 represents sending verification code successfully.
     * code:0 represents sending password failed. The account does not exist.
     * @param userNumber
     * @return Result
     */
    public Result forgetPasswordSendVerificationCode(String userNumber) throws MessagingException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (user != null)
            return sendVerificationCode(userNumber);
        else
            return new Result(0, "Forget password failed. The account does not exist", null);
    }

    /**
     * forget password check verification code.
     * code:1 represents checking verification code successfully.
     * code:0 represents the verification code is wrong.
     * @param userNumber
     * @param verificationCode
     * @return Result
     */
    public Result forgetPasswordCheckVerificationCode(String userNumber, String verificationCode) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (Objects.equals(verificationCode, "12345"))
            return new Result(1, "Checking the verification code succeeded", null);
        else
            return new Result(0, "Forget password failed. The verification code is wrong", null);
    }

    /**
     * update password.
     * code:1 represents updating password successfully.
     * code:0 represents updating password failed. The account does not exist.
     * @param userNumber
     * @param password
     * @return Result
     */
    public Result updatePassword(String userNumber, String password) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (user != null) {
            userMapper.updateUserPasswordByStudentNumber(userNumber, password);
            return new Result(1, "Updating password succeeded", null);
        } else
            return new Result(0, "Updating password failed. The account does not exist", null);
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
    public Result appealAccount(String userNumber, String appealContent) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (user != null) {
            if (Objects.equals(user.getUserStatus(), "valid"))
                return new Result(0, "Appealing account failed. The account is valid", null);

            List<Integer> appealOwnerList = appealMapper.selectAppealOwnerById(user.getUserId());
            if (appealOwnerList.contains(user.getUserId()))
                return new Result(1, "Appealing account failed. The account has been appealed", null);
            else {
                appealMapper.insertAppeal(user.getUserId(), appealContent);
                return new Result(2, "Appealing account succeeded", null);
            }
        } else
            return new Result(3, "Appeal account failed. The account does not exist", null);
    }
}