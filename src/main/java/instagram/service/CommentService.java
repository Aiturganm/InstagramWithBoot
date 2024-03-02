package instagram.service;

import instagram.entity.Comment;
import instagram.entity.User;

import java.util.List;

public interface CommentService{
    void saveComment(Comment comment, User user, Long postId);
    List<Comment> getComments(Long postId);

}
