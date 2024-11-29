package com.testpractice.socialnetwork.mappers;

import com.testpractice.socialnetwork.dtos.MessageDTO;
import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.mysql.Message;
import com.testpractice.socialnetwork.entities.mysql.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    @Autowired
    UserMapper userMapper;


    public Message toMessage(MessageDTO message) {
        User user = userMapper.fromUserDto(message.getSender());
        return new Message(user, message.getChatid(), message.getContent());
    }
    public MessageDTO toMessageDTO(Message message) {
        UserDto userDto = userMapper.toUserDto(message.getSender());
        return new MessageDTO(userDto, message.getChatId(), message.getContent());
    }
    public List<MessageDTO> toMessageDTOs(List<Message> messages) {
        return messages.stream().map(this::toMessageDTO).collect(Collectors.toList());
    }
}
