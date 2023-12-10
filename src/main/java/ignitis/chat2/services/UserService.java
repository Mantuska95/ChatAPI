package ignitis.chat2.services;

import ignitis.chat2.entities.ChatRoom;
import ignitis.chat2.entities.User;
import ignitis.chat2.repositories.ChatRoomRepository;
import ignitis.chat2.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public User createUser(User user) {
        user.setRole("USER");
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void joinChatRoom(Long userId, Long chatRoomId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("Chat room not found with id: " + chatRoomId));

        user.getChatRooms().add(chatRoom);
        userRepository.save(user);
    }

}
