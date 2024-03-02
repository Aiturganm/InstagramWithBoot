package instagram.repository;

import instagram.entity.Post;
import instagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @Query("select u from User u where u.id = :id")
    User findUser(Long id);

    @Query("delete from Post p where p.id = :id")
    void deletePostMy(Long id);
}
