package instagram.repository;

import instagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userName like (:name) and u.email like (:email)")
    User login(String name, String email);

    @Query("select u from User u where u.userName like (:name)")
    User findUserByName(String name);
}
