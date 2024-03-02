package instagram.controller;


import instagram.entity.Comment;
import instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getAllComments")
    public String getComments(Model model){
        List<Comment> comments = commentService.getComments(PostController.currentPost.getId());
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());
        return "post/comment-page";
    }

    @PostMapping
    public String comment(@ModelAttribute("newComment") Comment comment){
        commentService.saveComment(comment, UserController.currentUser, PostController.currentPost.getId());
        return "post/comment-page";
    }
}
