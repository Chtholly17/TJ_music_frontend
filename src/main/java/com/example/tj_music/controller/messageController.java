package com.example.tj_music.controller;


import com.example.tj_music.DTO.sendMessageDTO;
import com.example.tj_music.service.messageService;
import com.example.tj_music.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class messageController {
    @Autowired
    private messageService messageService;

    /**
     * 发送消息
     * 通过调用该接口以通过发送者和接收者的学号发送消息
     * @param sender_student_number 发送者学号
     * @param receiver_student_number 接收者学号
     * @param content 消息内容
     * @return 可能出现的错误信息
     */
    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestParam String sender_student_number,
                              @RequestParam String receiver_student_number,
                              @RequestParam String content) {
        sendMessageDTO sendMessageDTO=new sendMessageDTO();
        sendMessageDTO.setSender_student_number(sender_student_number);
        sendMessageDTO.setReceiver_student_number(receiver_student_number);
        sendMessageDTO.setContent(content);
        return messageService.insertMessage(sendMessageDTO);
    }

    /**
     * 获取简略消息列表
     * 通过用户学号来获得该用户的简略消息列表：包含发送者昵称、发送者头像、最后一条消息内容
     * @param user_student_number 用户学号
     * @return
     */
    @GetMapping("/getMessageBrief")
    public Result getMessageBrief(@RequestParam String user_student_number) {
        return messageService.getMessageBrief(user_student_number);
    }

    @GetMapping("/getMessageListSenderReceiver")
    public Result getMessageListSenderReceiver(@RequestParam String sender_student_number,
                                               @RequestParam String receiver_student_number) {
        return messageService.getMessageListSenderReceiver(sender_student_number,receiver_student_number);
    }
}
