package instagram.controller;

import instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    public static boolean isLike;
    @PostMapping
    public String like(@RequestParam("id") Long id, Model model){
        isLike = likeService.like(id, UserController.currentUser.getId());
        model.addAttribute("isLike", isLike);
        return "redirect:/api/post/findFollowerPost?id="+id;
    }
}
