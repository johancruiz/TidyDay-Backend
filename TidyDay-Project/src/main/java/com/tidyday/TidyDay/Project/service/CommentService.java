package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);
}
