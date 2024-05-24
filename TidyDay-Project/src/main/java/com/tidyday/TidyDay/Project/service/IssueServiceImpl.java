package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Issue;
import com.tidyday.TidyDay.Project.modal.Schedule;
import com.tidyday.TidyDay.Project.repository.IssueRepository;
import com.tidyday.TidyDay.Project.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()){
            return issue;
        }
        throw new Exception("No issues found with issueid" + issueId);
    }

    @Override
    public List<Issue> getIssueByScheduleId(Long scheduleId) throws Exception {
        return issueRepository.findByScheduleID(scheduleId);
    }

    @Override
    public Issue createIssue(IssueRequest issue, Long userid) throws Exception {
        Schedule schedule = scheduleService.getScheduleById(issue.getScheduleId());
        return null;
    }

    @Override
    public String deleteIssue(Long issueId, Long userid) throws Exception {
        return null;
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        return null;
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        return null;
    }
}
