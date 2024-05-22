package com.tidyday.TidyDay.Project.controller;

import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.service.ScheduleService;
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

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Schedule>>getSchedules(
            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tag,
            @RequestHeader("Authorization") String jwt

    ){
        User user=userService.findUserProfileJwt(Jwt);
        List<Schedule> Schedules=scheduleService.getScheduleByTeam(user,category,tag);
        return new ResponseEntity<>(Schedules, HttpStatus.OK);
    }

    @GetMapping("/{ScheduleId}")
    public ResponseEntity<Schedule>getSchedulebyId(
           @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt

    ){
        User user=userService.findUserProfileJwt(Jwt);
        Schedule schedule=scheduleService.getScheduleById(ScheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Schedule>createdschedule(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Schedule schedule

    ){
        User user=userService.findUserProfileJwt(Jwt);
        Schedule createdschedule=scheduleService.createSchedule(schedule,user);
        return new ResponseEntity<>(createdschedule, HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Schedule>updateschedule(
            @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Schedule schedule

    ){
        User user=userService.findUserProfileJwt(Jwt);
        Schedule updateschedule=scheduleService.updateSchedule(schedule,ScheduleId);
        return new ResponseEntity<>(updateschedule, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Schedule>deleteschedule(
            @PathVariable Long ScheduleId,
            @RequestHeader("Authorization") String jwt,

    ){
        User user=userService.findUserProfileJwt(Jwt);
        scheduleService.deleteSchedule(ScheduleId,user.getId());
        return new ResponseEntity<>(deleteschedule, HttpStatus.OK);
    }
}
