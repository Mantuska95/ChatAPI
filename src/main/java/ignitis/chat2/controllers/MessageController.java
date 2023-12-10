package ignitis.chat2.controllers;

import ignitis.chat2.entities.Message;
import ignitis.chat2.entities.User;
import ignitis.chat2.services.MessageService;
import ignitis.chat2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/send/{userId}/{chatRoomId}")
    public ResponseEntity<Message> sendMessage(
            @PathVariable Long userId,
            @PathVariable Long chatRoomId,
            @RequestBody String content
    ) {
        User user = userService.getUserById(userId);
        Message sentMessage = messageService.sendMessage(user, content, chatRoomId);
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chatRoom/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessagesByChatRoom(@PathVariable Long chatRoomId) {
        List<Message> messages = messageService.getMessagesByChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }
}
