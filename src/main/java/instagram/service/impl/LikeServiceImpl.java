package instagram.service.impl;

import instagram.entity.Like;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.repository.LikeRepo;
import instagram.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;

    @Override
    public boolean like(Long postId, Long userId) {
        Post post = likeRepo.findPost(postId);
        User user = likeRepo.findUser(userId);

        List<Like> likes = post.getLikes();
        Like like = user.getLike();

        boolean isLike = false;

        for (Like postLike : likes) {
            if (postLike.getId().equals(like.getId())) {
                likes.remove(postLike);
                isLike = true;
                break;
            }
        }

        if(!isLike){
            likes.add(like);
        }

        return isLike;
    }
}
