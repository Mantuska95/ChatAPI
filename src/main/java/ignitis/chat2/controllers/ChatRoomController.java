package ignitis.chat2.controllers;

import ignitis.chat2.entities.ChatRoom;
import ignitis.chat2.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-room")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody String name) {
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(name);
        return ResponseEntity.ok(createdChatRoom);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }
}
