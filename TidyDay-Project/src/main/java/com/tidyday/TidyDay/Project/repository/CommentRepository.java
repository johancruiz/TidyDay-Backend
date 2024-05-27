package com.tidyday.TidyDay.Project.repository;

import com.tidyday.TidyDay.Project.modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findCommentByIssueId(Long issueId);
}
