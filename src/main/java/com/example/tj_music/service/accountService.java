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

    /* login check
     * @param userNumber
     * @param password
     * @return Result
     */
    public Result loginCheck(String userNumber, String password) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (Objects.equals(user.getUserPassword(), password)) {
            result.setCode(1);
            result.setMsg("login success");
            result.setData(null);
        } else {
            result.setCode(0);
            result.setMsg("login failed");
            result.setData(null);
        }
        return result;
    }

    /* send verification code
     * @param userNumber
     * @return Result
     */
    public Result sendVerificationCode(String userNumber) throws MessagingException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
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
        result.setCode(1);
        result.setMsg("send verification code success");
        result.setData(null);
        return result;
    }

    /* register send verification code
     * @param userNumber
     * @return Result
     */
    public Result registerSendVerificationCode(String userNumber) throws MessagingException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (user == null) {
            result = sendVerificationCode(userNumber);
            return result;
        } else {
            result.setCode(0);
            result.setMsg("register failed, the account has been existed");
            result.setData(null);
        }
        return result;
    }

    /* register check verification code
     * @param userNumber
     * @param password
     * @param verificationCode
     * @return Result
     */
    public Result registerCheckVerificationCode(String userNumber, String password, String verificationCode) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (Objects.equals(verificationCode, "12345")) {
            userMapper.insertUser(userNumber, password, "user", "user", "user");
            result.setCode(1);
            result.setMsg("register success");
            result.setData(null);
        } else {
            result.setCode(0);
            result.setMsg("register failed, verification code is wrong");
            result.setData(null);
        }
        return result;
    }

    /* forget password send verification code
     * @param userNumber
     * @return Result
     */
    public Result forgetPasswordSendVerificationCode(String userNumber) throws MessagingException {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (user != null) {
            result = sendVerificationCode(userNumber);
            return result;
        } else {
            result.setCode(0);
            result.setMsg("forget password failed, the account does not exist");
            result.setData(null);
        }
        return result;
    }

    /* forget password check verification code
     * @param userNumber
     * @param verificationCode
     * @return Result
     */
    public Result forgetPasswordCheckVerificationCode(String userNumber, String verificationCode) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (Objects.equals(verificationCode, "12345")) {
            result.setCode(1);
            result.setMsg("check verification code success");
            result.setData(null);
        } else {
            result.setCode(0);
            result.setMsg("check verification code failed, verification code is wrong");
            result.setData(null);
        }
        return result;
    }

    /* update password
     * @param userNumber
     * @param password
     * @return Result
     */
    public Result updatePassword(String userNumber, String password) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (user != null) {
            userMapper.updateUserPasswordByStudentNumber(userNumber, password);
            result.setCode(1);
            result.setMsg("update password success");
            result.setData(null);
        } else {
            result.setCode(0);
            result.setMsg("update password failed, the account does not exist");
            result.setData(null);
        }
        return result;
    }

    /* appeal account
     * @param userNumber
     * @param appealContent
     * @return Result
     */
    public Result appealAccount(String userNumber, String appealContent) {
        User user = userMapper.selectUserByStudentNumber(userNumber);
        Result result = new Result();
        if (user != null) {
            List<Integer> appealOwnerList = appealMapper.selectAppealOwnerById(user.getUserId());
            if (appealOwnerList.contains(user.getUserId())) {
                result.setCode(0);
                result.setMsg("appeal account failed, the account has been appealed");
                result.setData(null);
            } else {
                appealMapper.insertAppeal(user.getUserId(), appealContent);
                result.setCode(1);
                result.setMsg("appeal account success");
                result.setData(null);
            }
        } else {
            result.setCode(0);
            result.setMsg("appeal account failed, the account does not exist");
            result.setData(null);
        }
        return result;
    }
}