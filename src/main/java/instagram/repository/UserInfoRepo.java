package instagram.repository;

import instagram.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

    @Query("select uf from UserInfo uf inner join User u on u.userInfo.id = uf.id where u.id = (:userId)")
    UserInfo findUserInfoByUserId(Long userId);

    @Modifying
    @Query("update UserInfo uf set uf.image = :urlImg where uf.id = :id")
    void changeImage(String urlImg, Long id);

    @Modifying
    @Query("update UserInfo uf set uf.image = :image where uf.id = :id")
    void deleteImage(String image, Long id);

}
