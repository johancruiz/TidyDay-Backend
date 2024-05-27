package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public Comment createComment(Long issueId, Long userId, String comment) throws Exception {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, long userId) throws Exception {

    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return null;
    }
}
