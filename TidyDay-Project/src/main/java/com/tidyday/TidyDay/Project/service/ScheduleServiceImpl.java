package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Chat;
import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl  implements  ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;


    @Override
    public Schedule createSchedule(Schedule schedule, User user) throws Exception {
        Schedule createdSchedule=new Schedule();

        createdSchedule.setOwner(user);
        createdSchedule.setTags(schedule.getTags());
        createdSchedule.setName(schedule.getName());
        createdSchedule.setCategory(schedule.getCategory());
        createdSchedule.setDescription(schedule.getDescription());
        createdSchedule.getTeam().add(user);

        Schedule savedSchedule=scheduleRepository.save(createdSchedule);

        Chat chat=new Chat();
        chat.setSchedule(savedSchedule);

        Chat scheduleChat = chatService.createChat(chat);
        savedSchedule.setChat(scheduleChat);

        return savedSchedule;
    }

    @Override
    public List<Schedule> getScheduleByTeam(User user, String category, String tag) throws Exception {
        List<Schedule> schedules=scheduleRepository.findByTeamContainingOrOwner(user, user);

        if (category!=null){
            schedules=schedules.stream().filter(schedule -> schedule.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        if (category!=null){
            schedules=schedules.stream().filter(schedule -> schedule.getTags().contains(tag))
                    .collect(Collectors.toList());
        }
        return schedules;
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) throws Exception {
        Optional<Schedule>optionalSchedule=scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isEmpty()){
            throw new Exception("Schedule not found");
        }

        return optionalSchedule.get();
    }

    @Override
    public void deleteSchedule(Long scheduleId, Long userId) throws Exception {
        getChatByScheduleId(scheduleId);
        //userService.
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    public Schedule updateSchedule(Schedule updatedSchedule, Long id) throws Exception {
        Schedule schedule=getScheduleById(id);

        schedule.setName(updatedSchedule.getName());
        schedule.setDescription(updatedSchedule.getDescription());
        schedule.setTags(updatedSchedule.getTags());

        return scheduleRepository.save(schedule);
    }

    @Override
    public void addUserToSchedule(Long scheduleId, Long userId) throws Exception {
        Schedule schedule = getScheduleById(scheduleId);
        User user = userService.findByUserId(userId);
        if (!schedule.getTeam().contains(user)){
            schedule.getChat().getUsers().add(user);
            schedule.getTeam().add(user);
        }
        scheduleRepository.save(schedule);
    }

    @Override
    public void removeUserFromSchedule(Long scheduleId, Long userId) throws Exception {
        Schedule schedule = getScheduleById(scheduleId);
        User user = userService.findByUserId(userId);
        if (!schedule.getTeam().contains(user)){
            schedule.getChat().getUsers().remove(user);
            schedule.getTeam().remove(user);
        }
        scheduleRepository.save(schedule);
    }

    @Override
    public Chat getChatByScheduleId(Long scheduleId) throws Exception {
        Schedule schedule = getScheduleById(scheduleId);

        return schedule.getChat();
    }

    @Override
    public List<Schedule> searchSchedules(String keyword, User user) throws Exception {

        return scheduleRepository.findByNameContainingAndTeamContains(keyword, user);
    }
}
