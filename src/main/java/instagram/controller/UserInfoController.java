package instagram.controller;

import instagram.entity.UserInfo;
import instagram.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/userInfo")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping()
    public String editUserInfo(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "userInfos/edit-userInfo";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("userInfo") UserInfo userInfo){
        UserInfo userInfoByUserId = userInfoService.findUserInfoByUserId(UserController.currentUser.getId());
        userInfoService.update(userInfoByUserId.getId(), userInfo);
        return "redirect:/api/users/profile";
    }

    @GetMapping("/changeImg")
    public String changeImg(Model model){
        model.addAttribute("userInfoImg", new UserInfo());
        return "userInfos/image-setting";
    }

    @PostMapping("/imageChange")
    public String imageChange(@ModelAttribute("userInfoImg") UserInfo userInfo){
        UserInfo userInfoByUserId = userInfoService.findUserInfoByUserId(UserController.currentUser.getId());
        userInfoService.changeImage(userInfo.getImage(), userInfoByUserId.getId());
        return "redirect:/api/users/profile";
    }

//    @GetMapping("/deleteImgGet")
//    public String imgDelete(Model model){
//        model.addAttribute("ImgDelete", new UserInfo());
//        return "userInfos/"
//    }

    @GetMapping("/deleteImg")
    public String deleteImage(){
        UserInfo userInfoByUserId = userInfoService.findUserInfoByUserId(UserController.currentUser.getId());
        userInfoService.deleteImage(userInfoByUserId.getId());
        return "redirect:/api/users/profile";
    }
}
