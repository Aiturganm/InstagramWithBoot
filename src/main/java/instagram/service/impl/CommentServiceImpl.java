package instagram.service.impl;

import instagram.entity.Comment;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.repository.CommentRepo;
import instagram.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;

    @Override
    public void saveComment(Comment comment, User user, Long postId) {
        User user1 = commentRepo.findUser(user.getId());
        Post post = commentRepo.findPost(postId);

        comment.setCreatedAt(LocalDate.now());
        post.getComments().add(comment);
        comment.setPost(post);
        comment.setUser(user);
        user1.getComments().add(comment);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        return commentRepo.getComments(postId);
    }
}
