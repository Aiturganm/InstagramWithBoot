package instagram.service;

import instagram.entity.Image;
import instagram.entity.Post;

import java.util.List;


public interface PostService {
    void createPost(Image image, Post post);
    List<Post> findAllPosts();
    Post findPostById(Long id);
    void updatePost(Long id, Post newPost);
    void deletePost(Long id);
}
