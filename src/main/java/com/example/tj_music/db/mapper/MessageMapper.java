package com.example.tj_music.db.mapper;

import com.example.tj_music.db.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MessageMapper {

    // select------------------------------------------------
    // get all messages
    @Select("select * from message")
    public List<Message> getAllMessage();

    // select by sender id
    @Select("select * from message where sender_id = #{senderId}")
    public List<Message> selectBySenderId(Integer senderId);

    // select by receiver id, and sort by time desc
    @Select("select * from message where receiver_id = #{receiverId} order by create_time desc")
    public List<Message> selectByReceiverIdDescTime(Integer receiverId);


    // select by sender id and receiver id by time desc
    @Select("select * from message where sender_id = #{senderId} and receiver_id = #{receiverId} order by create_time desc")
    public List<Message> selectBySenderIdAndReceiverIdDescTime(Integer senderId, Integer receiverId);

    // select by message id
    @Select("select * from message where message_id = #{messageId}")
    public Message selectByMessageId(Integer messageId);

    // select all the user that send message to the given receiver
    @Select("select distinct sender_id from message where receiver_id = #{receiverId}")
    public List<Integer> selectAllSenderIdByReceiverId(Integer receiverId);

    // insert------------------------------------------------
    // insert message
    @Insert("insert into message( sender_id,receiver_id, content) values(#{senderId}, #{receiverId}, #{content})")
    void insertMessage(Integer senderId, Integer receiverId, String content);
}
