package com.tidyday.TidyDay.Project.controller;

import com.tidyday.TidyDay.Project.modal.Chat;
import com.tidyday.TidyDay.Project.modal.Message;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.request.CreateMessageRequest;
import com.tidyday.TidyDay.Project.service.MessageService;
import com.tidyday.TidyDay.Project.service.ScheduleService;
import com.tidyday.TidyDay.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;


    @PostMapping("/send")
    public ResponseEntity<Message>sendMessage(@RequestBody CreateMessageRequest request)
        throws  Exception {

        User user = userService.findByUserId(request.getSenderId());

        Chat chats =  scheduleService.getScheduleById(request.getScheduleId()).getChat();


        if (chats==null)throw  new Exception("chats no found");

        Message sentMessage = messageService.sendMessage(request.getSenderId(),
                request.getScheduleId(),request.getContent());
        return  ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{scheduleId}")
    public  ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long scheduleId)
        throws  Exception {
        List<Message> messages =  messageService.getMessageByScheduleId(scheduleId);
        return ResponseEntity.ok( messages);
    }
}
