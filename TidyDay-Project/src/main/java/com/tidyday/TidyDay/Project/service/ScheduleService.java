package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Chat;
import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.modal.User;

import java.util.List;

public interface ScheduleService {

    Schedule createSchedule(Schedule schedule, User user) throws Exception;

    List<Schedule> getScheduleByTeam(User user, String category, String tag) throws Exception;

    Schedule getScheduleById(Long scheduleId) throws Exception;

    void deleteSchedule(Long scheduleId, Long userId) throws Exception;

    Schedule updateSchedule( Schedule updatedSchedule, Long id ) throws Exception;

    void addUserToSchedule(Long scheduleId, Long userId ) throws Exception;
    void removeUserFromSchedule(Long scheduleId, Long userId ) throws Exception;

    Chat getChatByScheduleId(Long scheduleId) throws Exception;
}
