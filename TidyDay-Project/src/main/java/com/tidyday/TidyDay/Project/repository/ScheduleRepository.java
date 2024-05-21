package com.tidyday.TidyDay.Project.repository;

import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule>findByOwner(User user);

    List<Schedule> findByNameContainingAndTeamContaining(String partialName, User user);

    List<Schedule> findScheduleByTeam(@Param("user") User user);

    @Query("SELECT s From Schedule s join p.team t where t=:user")
     List<Schedule> findProjectByTeam(@Param("user") User user );

    List<Schedule> findByTeamContainingOrOwner(User user, User owner);
}
