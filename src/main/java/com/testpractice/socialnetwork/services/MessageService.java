package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.dtos.MessageDTO;
import com.testpractice.socialnetwork.entities.mysql.Message;
import com.testpractice.socialnetwork.mappers.MessageMapper;
import com.testpractice.socialnetwork.reps.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageMapper messageMapper;


    public void saveMessageDTO(MessageDTO message) {
        Message message1 = messageMapper.toMessage(message);
        messageRepository.save(message1);
    }
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
    public MessageDTO saveMessageWithReturnId(MessageDTO messageDTO) {
        Message message1 = messageMapper.toMessage(messageDTO);
        messageRepository.save(message1);
        messageDTO.setId(message1.getId());
        return messageDTO;
    }
    public List<MessageDTO> getMessagesByChatId(String chatId) {
        List<Message> messages = messageRepository.findByChatId(chatId);
        return messageMapper.toMessageDTOs(messages);
    }
}
