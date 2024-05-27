package com.tidyday.TidyDay.Project.controller;

import com.tidyday.TidyDay.Project.modal.Issue;
import com.tidyday.TidyDay.Project.modal.IssueDTO;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.request.IssueRequest;
import com.tidyday.TidyDay.Project.response.AuthResponse;
import com.tidyday.TidyDay.Project.response.MessageResponse;
import com.tidyday.TidyDay.Project.service.IssueService;
import com.tidyday.TidyDay.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("({issueId}")
    public ResponseEntity<Issue>getIssueById(@PathVariable Long issueId)throws Exception {
        return  ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/schedule/{scheduleId}")
    public  ResponseEntity<List<Issue>> getIssueByscheduleId(@PathVariable Long scheduleId)
        throws  Exception {
        return  ResponseEntity.ok(issueService.getIssueByScheduleId(scheduleId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createdIssue(@RequestBody IssueRequest issue,
                                                 @RequestHeader("Authorization")String token)
            throws Exception{
        
        User tokenUser=userService.findUserProfileByJwt(token);
        User user = userService.findByUserId(tokenUser.getId());


            Issue  createdIssue = issueService.createIssue(issue,tokenUser);
            IssueDTO issueDTO= new IssueDTO();
            issueDTO.setDescription(createdIssue.getDescription());
            issueDTO.setDueDate(createdIssue.getDueDate());
            issueDTO.setId(createdIssue.getId());
            issueDTO.setPriority(createdIssue.getPriority());
            issueDTO.setSchedule(createdIssue.getSchedule());
            issueDTO.setScheduleId(createdIssue.getScheduleID());
            issueDTO.setStatus(createdIssue.getStatus());
            issueDTO.setTitle(createdIssue.getTitle());
            issueDTO.setTags(createdIssue.getTags());
            issueDTO.setAssignee(createdIssue.getAssignee());

            return  ResponseEntity.ok(issueDTO);
            
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                       @RequestHeader("Authorization") String token)
            throws  Exception{
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId,user.getId());

        MessageResponse res=new MessageResponse();
        res.setMessage("Issue Deleted");

        return ResponseEntity.ok(res);

    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId)

        throws  Exception{
        Issue issue = issueService.addUserToIssue(issueId,userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public  ResponseEntity<Issue>updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId)throws  Exception{
        Issue issue= issueService.updateStatus(issueId,status);
        return ResponseEntity.ok(issue);
    }


}
