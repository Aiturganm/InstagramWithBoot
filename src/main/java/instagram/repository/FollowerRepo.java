package instagram.repository;

import instagram.entity.Follower;
import instagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowerRepo extends JpaRepository<Follower, Long> {

    @Query("select u from User u where u.userName ilike (:userName)")
    List<User> search(String userName);

    @Query("select u from User u where u.id = (:id)")
    User findUser(Long id);

    @Query("select f.subscribers from Follower f inner join User u on u.follower.id = f.id where u.id = (:id)")
    List<Long> getAllSubscribersByUserId(Long id);

    @Query("select f.subscriptions from Follower f inner join User u on u.follower.id = f.id where u.id = (:id)")
    List<Long> getAllSubscriptionsByUserId(Long id);
}
