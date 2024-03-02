package instagram.service.impl;

import instagram.controller.UserController;
import instagram.entity.Comment;
import instagram.entity.Image;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.repository.PostRepo;
import instagram.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    @Override
    public void createPost(Image image, Post post) {
        List<Image> images = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        post.setCreatedAt(LocalDate.now());
        post.setComments(comments);
        image.setPost(post);
        post.setImage(image);
        UserController.currentUser.getPosts().add(post);
        post.setUser(UserController.currentUser);
        User user1 = postRepo.findUser(UserController.currentUser.getId());
        user1.getPosts().add(post);
        post.setUser(user1);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public Post findPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        Post post = postRepo.findById(id).orElseThrow();
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deletePostMy(id);
    }
}
