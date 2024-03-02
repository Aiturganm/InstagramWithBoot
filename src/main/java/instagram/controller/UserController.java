package instagram.controller;

import instagram.entity.Post;
import instagram.entity.User;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    public static User currentUser;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("allUsers", users);
        return "users/users_card";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newUser", new User());
        return "users/new-user";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("newUser") User user) {
        String isSave = userService.save(user);
        currentUser = user;
        switch (isSave) {
            case "number":
                return "users/signup-error";
            case "name":
                return "users/signup-errorname";
            case "email":
                return "users/signup-erroremail";
        }
        return "users/home-page";
//        return "redirect:/api/users/profile?id=" + user.getId();
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginUser", new User());
        return "users/login-page";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("loginUser") User user) {
        currentUser = userService.login(user);
        if (currentUser == null) {
            return "users/login-error";
        }
        return "users/home-page";
//        return "redirect:/api/users/profile?id=" + loginUser.getId();
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        model.addAttribute("user", currentUser);
        model.addAttribute("userInfo", currentUser.getUserInfo());
        model.addAttribute("followers", currentUser.getFollower());
        model.addAttribute("image", currentUser.getUserInfo().getImage());
        List<Post> postList = new ArrayList<>();
        for (int i = currentUser.getPosts().size()-1; i >=0; i--) {
            postList.add(currentUser.getPosts().get(i));
        }
        model.addAttribute("posts", postList);
        model.addAttribute("like", currentUser.getLike());
        return "users/user-profile";
    }

    @GetMapping("/getUser")
    public String getUserByName(@RequestParam("name") String name, Model model) {
        User user = userService.findUserByName(name);
        model.addAttribute("user", user);
        return "users/userByName";
    }

//    @GetMapping("/updateUser")
//    public String updateUser(Model model){
//        model.addAttribute("updateUser", )
//    }
}
