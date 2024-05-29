package com.tidyday.TidyDay.Project.controller;

import com.tidyday.TidyDay.Project.modal.Comment;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.request.CreateCommentRequest;
import com.tidyday.TidyDay.Project.response.MessageResponse;
import com.tidyday.TidyDay.Project.service.CommentService;
import com.tidyday.TidyDay.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<Comment>createComment(

            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization")String jwt)throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Comment createdcomment = commentService.createComment(
                req.getIssueId(),
                user.getId(),
                req.getContent());
        return  new ResponseEntity<>(createdcomment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long CommentId,
                                                         @RequestHeader("Authorization")String jwt)
        throws  Exception{
        User user= userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());
        MessageResponse res=new MessageResponse();
        res.setMessage("comment deleted Successfully");
        return  new ResponseEntity<>(res,HttpStatus.OK);

    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>>getCommentsByIssueId(@PathVariable Long issueId){
        List<Comment> comments=commentService.findCommentByIssueId(issueId);
        return  new ResponseEntity<>(comments,HttpStatus.OK);
    }


}
