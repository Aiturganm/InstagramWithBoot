package instagram.controller;

import instagram.entity.*;
import instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    public static Post currentPost;

    @GetMapping("/new")
    public String createPost(Model model){
        model.addAttribute("newPost", new Post());
        model.addAttribute("newImage", new Image());
//        model.addAttribute("comments", new Comment());
//        model.addAttribute("like", new Like());
//        model.addAttribute("user", UserController.currentUser);
        return "post/create-post";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute("newPost") Post post, @ModelAttribute("newImage") Image image, Model model){
        postService.createPost(image, post);
        model.addAttribute("post", post);
        model.addAttribute("user", UserController.currentUser);
        return "users/user-profile";
    }

    @GetMapping("/allPosts")
    public String findAllPosts(Model model){
        List<Post> allPosts = postService.findAllPosts();
        model.addAttribute("posts", allPosts);
        return "post/allPost";
    }

    @GetMapping("/findPost")
    public String findPostById(@RequestParam("id") Long id, Model model){
        Post postById = postService.findPostById(id);
        model.addAttribute("postById", postById);
        return "post/post-by-id";
    }

     @GetMapping("/findFollowerPost")
    public String findFollowerPostById(@RequestParam("id") Long id, @ModelAttribute("isLike") User user, Model model){
        Post postById = postService.findPostById(id);
        currentPost=postById;
        model.addAttribute("postById", postById);
        model.addAttribute("likes", postById.getLikes());
        model.addAttribute("isLike", user.getLike().getIsLike());
        return "post/post-by-id-follower";
    }



    @GetMapping("/updatePost")
    public String updatePost(Model model, @RequestParam("id") Long id){
        model.addAttribute("updatePost", new Post());
        model.addAttribute("postId", id);
        return "post/update-page";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("updatePost") Post post, @RequestParam("id") Long id){
        postService.updatePost(id, post);
        return "redirect:/api/post/findPost?id="+id;
    }

//    @GetMapping("/delete")
//    public String delete(@RequestParam("id") Long id){
//        postService.deletePost(id);
//        return "redirect:/api/users/profile";
//    }

    @GetMapping("/delete")
    public String showDeleteConfirmationPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "post/delete-confirmation"; // Вернуть страницу подтверждения удаления
    }

    @PostMapping("/deletePost")
    public String delete(@RequestParam("id") Long id) {
        postService.deletePost(id);
        return "redirect:/api/users/profile";
    }
}
