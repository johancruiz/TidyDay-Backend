package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Chat;
import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.modal.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl  implements  ScheduleService{


    @Override
    public Schedule createSchedule(Schedule schedule, User user) throws Exception {
        return null;
    }

    @Override
    public List<Schedule> getScheduleByTeam(User user, String category, String tag) throws Exception {
        return null;
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) throws Exception {
        return null;
    }

    @Override
    public void deleteSchedule(Long scheduleId, Long userId) throws Exception {

    }

    @Override
    public Schedule updateSchedule(Schedule updatedSchedule, Long id) throws Exception {
        return null;
    }

    @Override
    public void addUserToSchedule(Long scheduleId, Long userId) throws Exception {

    }

    @Override
    public void removeUserFromSchedule(Long scheduleId, Long userId) throws Exception {

    }

    @Override
    public Chat getChatByScheduleId(Long scheduleId) throws Exception {
        return null;
    }
}
