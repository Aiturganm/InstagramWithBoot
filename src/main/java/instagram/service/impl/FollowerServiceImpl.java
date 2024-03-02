package instagram.service.impl;

import instagram.entity.Follower;
import instagram.entity.User;
import instagram.repository.FollowerRepo;
import instagram.service.FollowerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepo followerRepo;
    @Override
    public List<User> search(String userName) {
        return followerRepo.search("%"+userName+"%");
    }

    @Override
    public boolean subscribe(Long userId, Long subscribeUserId) {
        User user = followerRepo.findUser(userId);
        Follower follower = user.getFollower();
        List<Long> subscribers = follower.getSubscribers();


        User me = followerRepo.findUser(subscribeUserId);
        Follower myFollower = me.getFollower();
        List<Long> subscriptions = myFollower.getSubscriptions();

        boolean isSubscribe = false;

        for(Long id : subscribers){
            if(id.equals(subscribeUserId)){
                subscribers.remove(subscribeUserId);
                subscriptions.remove(userId);
                isSubscribe = true;
                break;
            }
        }
        if (!isSubscribe) {
            subscribers.add(subscribeUserId);
            subscriptions.add(userId);
        }
        return isSubscribe;
    }

    @Override
    public List<Long> getAllSubscribersByUserId(Long id) {
        return followerRepo.getAllSubscribersByUserId(id);
    }

    @Override
    public List<Long> getAllSubscriptionsByUserId(Long id) {
        return followerRepo.getAllSubscriptionsByUserId(id);
    }
}
