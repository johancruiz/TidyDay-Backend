package com.tidyday.TidyDay.Project.service;


import com.tidyday.TidyDay.Project.modal.Issue;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.request.IssueRequest;


import java.util.List;


public interface IssueService {
    Issue getIssueById(Long userId) throws Exception;

    List<Issue> getIssueByScheduleId(Long ScheduleId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userid) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
