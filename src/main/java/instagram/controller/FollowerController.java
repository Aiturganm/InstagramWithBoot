package instagram.controller;

import instagram.entity.Post;
import instagram.entity.User;
import instagram.service.FollowerService;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/follower")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;
    private final UserService userService;

    public static User followerUser;
    public static boolean subscribed;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("searchUser", new User());
        return "followers/search";
    }


    @PostMapping("/userSearch")
    public String userSearch(@ModelAttribute("searchUser") User user, Model model){
        List<User> userList = followerService.search(user.getUserName());
        model.addAttribute("searchedUsers", userList);
        return "followers/search";
    }

    @GetMapping("/followerPage")
    public String followerPage(@RequestParam("id") Long id, Model model){
        followerUser = userService.getUserById(id);
        List<Post> posts = new ArrayList<>();
        for (int i = followerUser.getPosts().size()-1; i >= 0; i--) {
            posts.add(followerUser.getPosts().get(i));
        }
        model.addAttribute("follower", followerUser);
        model.addAttribute("subscribed", subscribed);
        model.addAttribute("followersPosts", posts);
        return "followers/follower-page";
    }

    @GetMapping("/followerPageNotParam")
    public String followerPageNotParam(Model model){
        model.addAttribute("follower", followerUser);
        return "followers/follower-page";
    }


    @PostMapping("/subscribe")
    public String subscribe(){
        subscribed = followerService.subscribe(followerUser.getId(), UserController.currentUser.getId());
        return "redirect:/api/follower/followerPage?id="+followerUser.getId();
    }

    @GetMapping("/getSubscribers")
    public String getSubscribers(Model model){
        List<Long> allSubscribersByUserId = followerService.getAllSubscribersByUserId(UserController.currentUser.getId());
        List<User> subscribers = new ArrayList<>();
        for (Long id : allSubscribersByUserId) {
            User userById = userService.getUserById(id);
            subscribers.add(userById);
        }
        model.addAttribute("allSubscribers", subscribers);
        return "users/subscribers";
    }


    @GetMapping("/getSubscribersFollower")
    public String getSubscribersFollower(Model model){
        List<Long> allSubscribersByUserId = followerService.getAllSubscribersByUserId(followerUser.getId());
        List<User> subscribers = new ArrayList<>();
        for (Long id : allSubscribersByUserId) {
            User userById = userService.getUserById(id);
            subscribers.add(userById);
        }
        model.addAttribute("allSubscribers", subscribers);
        return "followers/subscribers";
    }

    @GetMapping("/getSubscriptions")
    public String getSubscriptions(Model model){
        List<Long> allSubscriptionsByUserId = followerService.getAllSubscriptionsByUserId(UserController.currentUser.getId());
        List<User> subscriptions = new ArrayList<>();
        for (Long id : allSubscriptionsByUserId) {
            User userById = userService.getUserById(id);
            subscriptions.add(userById);
        }
        model.addAttribute("allSubscriptions", subscriptions);
        return "users/subscriptions";
    }

    @GetMapping("/getSubscriptionsFollower")
    public String getSubscriptionsFollower(Model model){
        List<Long> allSubscriptionsByUserId = followerService.getAllSubscriptionsByUserId(followerUser.getId());
        List<User> subscriptions = new ArrayList<>();
        for (Long id : allSubscriptionsByUserId) {
            User userById = userService.getUserById(id);
            subscriptions.add(userById);
        }
        model.addAttribute("allSubscriptions", subscriptions);
        return "followers/subscriptions";
    }

}
