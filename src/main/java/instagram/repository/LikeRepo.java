package instagram.repository;

import instagram.entity.Like;
import instagram.entity.Post;
import instagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {

    @Query("select p from Post p where p.id = (:postId)")
    Post findPost(Long postId);

    @Query("select u from User u where u.id = (:userId)")
    User findUser(Long userId);

}
