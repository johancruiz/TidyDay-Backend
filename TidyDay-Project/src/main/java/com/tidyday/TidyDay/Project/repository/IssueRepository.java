package com.tidyday.TidyDay.Project.repository;

import com.tidyday.TidyDay.Project.modal.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    public List<Issue>findByScheduleId(Long id);

}
