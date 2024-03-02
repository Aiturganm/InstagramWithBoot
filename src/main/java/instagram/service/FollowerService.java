package instagram.service;

import instagram.entity.User;

import java.util.List;

public interface FollowerService {
    List<User> search(String userName);
    boolean subscribe(Long userId, Long subscribeUserId);
    List<Long> getAllSubscribersByUserId(Long id);
    List<Long> getAllSubscriptionsByUserId(Long id);
}
