package ignitis.chat2.controllers;

import ignitis.chat2.entities.User;
import ignitis.chat2.services.MessageService;
import ignitis.chat2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/joinChatRoom/{userId}/{chatRoomId}")
    public ResponseEntity<Void> joinChatRoom(@PathVariable Long userId, @PathVariable Long chatRoomId) {
        userService.joinChatRoom(userId, chatRoomId);
        return ResponseEntity.noContent().build();
    }
}
