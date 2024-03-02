package instagram.repository;

import instagram.entity.Comment;
import instagram.entity.Post;
import instagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("select u from User u where u.id = (:userId)")
    User findUser(Long userId);

    @Query("select p from Post p where p.id = (:postId)")
    Post findPost(Long postId);
    @Query("select c from Comment c where c.post.id = (:postId)")
    List<Comment> getComments(Long postId);
}
