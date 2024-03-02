package instagram.service.impl;

import instagram.entity.*;
import instagram.repository.UserRepo;
import instagram.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public String save(User user) {
        Follower follower = new Follower();
        UserInfo userInfo = new UserInfo();
        Like like = new Like();
        like.setIsLike(false);
        userInfo.setImage("https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg");
        user.setFollower(follower);
        follower.setUser(user);
        user.setUserInfo(userInfo);
        user.setLike(like);
        like.setUser(user);
        try {
            userRepo.save(user);
            return "saved";
        } catch (Exception e) {
            return "number";
        }
    }

    @Override
    public User login(User user) {
        try {
            return userRepo.login(user.getUserName(), user.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserById(Long id) {
        if (!findAll().isEmpty()) {
            return userRepo.findById(id).orElse(null);
        } else return null;
    }

    @Override
    public User findUserByName(String name) {
        if (!findAll().isEmpty()) {
            return userRepo.findUserByName(name);
        } else return null;
    }

    @Override
    public void updateUser(Long userId, User newUser) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null){
            user.setUserName(newUser.getUserName());
            user.setEmail(newUser.getEmail());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setPassword(newUser.getPassword());
            user.setImage(newUser.getImage());
        }
    }
}
