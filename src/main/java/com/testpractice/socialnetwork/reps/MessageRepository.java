package com.testpractice.socialnetwork.reps;

import com.testpractice.socialnetwork.entities.Message;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(@Size(max = 45) @NotNull String chatId);

}