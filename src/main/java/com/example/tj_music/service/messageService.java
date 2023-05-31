package com.example.tj_music.service;

import com.example.tj_music.DTO.sendMessageDTO;
import com.example.tj_music.VO.GetMessageBriefListVO;
import com.example.tj_music.VO.GetMessageListVO;
import com.example.tj_music.db.entity.Message;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.db.mapper.MessageMapper;
import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.utils.Result;
import org.python.netty.handler.codec.MessageAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class messageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;


    // insert message by sender and receiver's student number and content
    public Result insertMessage(sendMessageDTO sendMessageDTO) {
        // get the user by student number
        User sender=userMapper.selectUserByStudentNumber(sendMessageDTO.getSender_student_number());
        if(sender==null){
            return Result.fail("sender does not exist");
        }
        User receiver=userMapper.selectUserByStudentNumber(sendMessageDTO.getReceiver_student_number());
        if(receiver==null){
            return Result.fail("receiver does not exist");
        }
        messageMapper.insertMessage(sender.getUserId(), receiver.getUserId(), sendMessageDTO.getContent());
        return Result.success("insert message successfully");
    }

    // get message brief by user's student number
    public Result getMessageBrief(String user_student_number) {
        User receiver=userMapper.selectUserByStudentNumber(user_student_number);
        if(receiver==null){
            return Result.fail("user does not exist");
        }
        List<GetMessageBriefListVO> getMessageBriefListVOList=new ArrayList<>();

        // get all the users that have sent message to the receiver
        List<Integer> senderList = messageMapper.selectAllSenderIdByReceiverId(receiver.getUserId());
        for(int i=0;i<senderList.size();i++){
            // for each sender, get the user information
            User sender=userMapper.selectUserById(senderList.get(i));
            // get the newest message content
            List<Message> all_message=messageMapper.selectMessageBetweenTwoUserTimeDescLimit(sender.getUserId(),receiver.getUserId(),1);
            getMessageBriefListVOList.add(new GetMessageBriefListVO(sender.getUserStudentNumber(),sender.getUserNickname(),sender.getUserProfileImageFilename(),all_message.get(0).getContent()));
        }

        return Result.success(getMessageBriefListVOList);
    }

    // get the message list by sender and receiver's student number and descending time
    public Result getMessageListSenderReceiver(String sender_student_number, String receiver_student_number) {
        User sender=userMapper.selectUserByStudentNumber(sender_student_number);
        if(sender==null){
            return Result.fail("sender does not exist");
        }
        User receiver=userMapper.selectUserByStudentNumber(receiver_student_number);
        if(receiver==null){
            return Result.fail("receiver does not exist");
        }
        List<Message> messageList=messageMapper.selectBySenderIdAndReceiverIdDescTime(sender.getUserId(),receiver.getUserId());
        return Result.success(messageList);
    }

    // get the message list by sender and receiver's student number and ascending time with limit
    public Result getMessageListSenderReceiverLimit(String sender_student_number, String receiver_student_number, int limit) {
        User sender=userMapper.selectUserByStudentNumber(sender_student_number);
        if(sender==null){
            return Result.fail("sender does not exist");
        }
        User receiver=userMapper.selectUserByStudentNumber(receiver_student_number);
        if(receiver==null){
            return Result.fail("receiver does not exist");
        }
        List<Message> messageList=messageMapper.selectBySenderIdAndReceiverIdDescTimeWithLimit(sender.getUserId(),receiver.getUserId(),limit);
        return Result.success(messageList);
    }


    // get the message list given 2 user's student number and ascending time with limit
    public Result getMessageList2UserLimit(String user1_student_number, String user2_student_number, int limit) {
        User user1=userMapper.selectUserByStudentNumber(user1_student_number);
        if(user1==null){
            return Result.fail("user1 does not exist");
        }
        User user2=userMapper.selectUserByStudentNumber(user2_student_number);
        if(user2==null){
            return Result.fail("user2 does not exist");
        }
        List<Message> messageList=messageMapper.selectMessageBetweenTwoUserTimeDescLimit(user1.getUserId(),user2.getUserId(),limit);
        List<GetMessageListVO> ret=new ArrayList<>();
        for(int i=0;i<messageList.size();i++){
            GetMessageListVO getMessageListVO=new GetMessageListVO();
            if(messageList.get(i).getSenderId()==user1.getUserId()){
                getMessageListVO.setSender_student_number(user1_student_number);
            }
            else{
                getMessageListVO.setSender_student_number(user2_student_number);
            }
            getMessageListVO.setMessage_content(messageList.get(i).getContent());
            getMessageListVO.setMessage_time(messageList.get(i).getCreateTime());
            ret.add(getMessageListVO);
        }
        return Result.success(ret);
    }
}
