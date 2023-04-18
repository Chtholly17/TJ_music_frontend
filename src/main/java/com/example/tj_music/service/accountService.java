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
import java.util.Random;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.Authenticator;
import javax.mail.internet.MimeMessage;


@Service
public class accountService {
    @Autowired
    private AppealMapper appealMapper;
    @Autowired
    private UserMapper userMapper;

    private String verificationCode;

    /**
     * logout.
     * code:1 represents logout succeeded.
     * code:0 represents logout failed. The user does not exist.
     * @param userNumber
     * @return Result
     */
    public Result logout(String userNumber) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (user == null)
            return new Result(0, "Logout failed. The user does not exist.", null);

        userMapper.updateUserStatusById("normal", user.getUserId());
        return new Result(1, "Logout succeeded.", null);
    }

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
        else if (Objects.equals(user.getUserStatus(), "online"))
            return new Result(3, "Login failed. The user is online.", null);
        else if (Objects.equals(user.getUserPassword(), password)) {
            userMapper.updateUserStatusById("online", user.getUserId());
            return new Result(1, "Login succeeded.", null);
        }
        else
            return new Result(2, "Login failed. The password is incorrect.", null);
    }

    /**
     * send verification code.
     * code:1 represents the verification code sent successfully.
     * @param userNumber
     * @return Result
     */
    public Result sendVerificationCode(String userNumber) throws MessagingException, AddressException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Random random = new Random();
        this.verificationCode = String.valueOf(random.nextInt(89999) + 10000);
        String email = userNumber + "@tongji.edu.cn";
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "SMTP");//发送邮件协议
        properties.setProperty("mail.host", "smtp.qq.com");//发送邮件的服务器地址
        properties.setProperty("mail.smtp.auth", "true");//需要验证

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("982017264@qq.com", "xhamshqextmpbbcd");
            }
        };

        Session session = Session.getInstance(properties, auth);
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress("982017264@qq.com"));//设置发送人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));//设置接收人
        message.setSubject("邮箱验证");//设置邮件主题
        message.setText("你的验证码为：" + this.verificationCode + "。请注意，验证码有效时间为2分钟！！！");//设置邮件内容
        Transport.send(message);

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
     * code:2 represents register failed. The password is not the same.
     * code:1 represents register succeeded.
     * code:0 represents register failed. The verification code is wrong.
     * @param userNumber
     * @param password
     * @param checkPassword
     * @param verificationCode
     * @return Result
     */
    public Result registerCheckVerificationCode(String userNumber, String password, String verificationCode, String checkPassword) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        if (Objects.equals(verificationCode, this.verificationCode)) {
            if (!Objects.equals(password, checkPassword))
                return new Result(2, "Register failed. The password is not the same", null);

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