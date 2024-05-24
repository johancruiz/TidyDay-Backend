package com.tidyday.TidyDay.Project.controller;

import com.tidyday.TidyDay.Project.modal.Chat;
import com.tidyday.TidyDay.Project.modal.Invitation;
import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.request.InviteRequest;
import com.tidyday.TidyDay.Project.response.MessageResponse;
import com.tidyday.TidyDay.Project.service.InvitationService;
import com.tidyday.TidyDay.Project.service.ScheduleService;
import com.tidyday.TidyDay.Project.service.UserService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Schedule>>getSchedules(
            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tag,
            @RequestHeader("Authorization") String jwt

    )throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        List<Schedule> Schedules=scheduleService.getScheduleByTeam(user,category,tag);
        return new ResponseEntity<>(Schedules, HttpStatus.OK);
    }

    @GetMapping("/{ScheduleId}")
    public ResponseEntity<Schedule>getScheduleById(
           @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt

    )throws  Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Schedule schedule=scheduleService.getScheduleById(ScheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Schedule>createSchedule(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Schedule schedule

    )throws  Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Schedule createdschedule=scheduleService.createSchedule(schedule,user);
        return new ResponseEntity<>(createdschedule, HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Schedule>updateSchedule(
            @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Schedule schedule

    )throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Schedule updateschedule=scheduleService.updateSchedule(schedule,ScheduleId);
        return new ResponseEntity<>(updateschedule, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<MessageResponse>deleteSchedule(
            @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt

    )throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        scheduleService.deleteSchedule(ScheduleId,user.getId());
        MessageResponse res= new MessageResponse("Schedule deleted succesfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Schedule>> searchSchedules (
            @RequestParam(required = false)String keyword,

            @RequestHeader("Authorization") String jwt

    )throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        List<Schedule> Schedules=scheduleService.searchSchedules(keyword,user);
        return new ResponseEntity<>(Schedules, HttpStatus.OK);
    }

    @GetMapping("/{ScheduleId}/chat")
    public ResponseEntity<Chat>getChatByScheduleId(
            @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt

    )throws  Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Chat chat=scheduleService.getChatByScheduleId(ScheduleId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse>inviteSchedule(
            @RequestBody InviteRequest req,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Schedule schedule

    )throws  Exception {
        User user=userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getScheduleId());
        MessageResponse res = new MessageResponse("User invitation sent");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation>acceptInviteSchedule(
            @RequestBody String token,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Schedule schedule

    )throws  Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        scheduleService.addUserToSchedule(invitation.getScheduleId(), user.getId());


        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }
}
