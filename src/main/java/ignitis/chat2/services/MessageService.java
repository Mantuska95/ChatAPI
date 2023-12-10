package ignitis.chat2.services;

import ignitis.chat2.entities.Message;
import ignitis.chat2.entities.User;
import ignitis.chat2.repositories.ChatRoomRepository;
import ignitis.chat2.repositories.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Message sendMessage(User user, String content, Long chatRoomId) {
        Message message = new Message();
        message.setUser(user);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setAnonymous(false); // Assuming default is non-anonymous
        message.setChatRoom(chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("Chat room not found with id: " + chatRoomId)));
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatRoom(Long chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId);
    }
}
