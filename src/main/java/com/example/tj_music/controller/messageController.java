package com.example.tj_music.controller;


import com.example.tj_music.DTO.sendMessageDTO;
import com.example.tj_music.service.messageService;
import com.example.tj_music.utils.Result;
import jnr.ffi.annotations.In;
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
     * @param senderStudentNumber 发送者学号
     * @param receiverStudentNumber 接收者学号
     * @param content 消息内容
     * @return 可能出现的错误信息
     */
    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestParam String senderStudentNumber,
                              @RequestParam String receiverStudentNumber,
                              @RequestParam String content) {
        sendMessageDTO sendMessageDTO=new sendMessageDTO();
        sendMessageDTO.setSender_student_number(senderStudentNumber);
        sendMessageDTO.setReceiver_student_number(receiverStudentNumber);
        sendMessageDTO.setContent(content);
        return messageService.insertMessage(sendMessageDTO);
    }

    /**
     * 获取简略消息列表
     * 通过用户学号来获得该用户的简略消息列表：包含发送者昵称、发送者头像、最后一条消息内容
     * @param userStudentNumber 用户学号
     * @return
     */
    @GetMapping("/getMessageBrief")
    public Result getMessageBrief(@RequestParam String userStudentNumber) {
        return messageService.getMessageBrief(userStudentNumber);
    }


    /**
     * 获取两个用户间消息列表
     * 提供两个用户的学号，以及获取消息的数量，返回两个用户间的消息列表
     * @param user1_student_number 发送者学号
     * @param use2_student_number 接收者学号
     * @param limit 获取消息的数量上限
     * @return
     */
    @GetMapping("/getMessageList")
    public Result getMessageListTwoUser(@RequestParam String user1_student_number,
                                        @RequestParam String user2_student_number){
//        return messageService.getMessageListSenderReceiverLimit(user1_student_number,user2_student_number,limit);
        return messageService.getMessageList2UserLimit(user1_student_number,user2_student_number,10000);
    }


}
