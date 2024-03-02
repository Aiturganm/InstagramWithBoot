package instagram.service.impl;

import instagram.entity.UserInfo;
import instagram.repository.UserInfoRepo;
import instagram.service.UserInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepo userInfoRepo;
    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoRepo.findUserInfoByUserId(userId);
    }

    @Override
    public String update(Long id, UserInfo newUserInfo) {
        if(findUserInfoByUserId(id)!=null) {
            UserInfo userInfo = userInfoRepo.findById(id).orElseThrow();
            userInfo.setFullName(newUserInfo.getFullName());
            userInfo.setBiography(newUserInfo.getBiography());
            userInfo.setGender(newUserInfo.getGender());
            userInfo.setImage(newUserInfo.getImage());
            return "updated";
        }else return null;
    }

    @Override
    public void changeImage(String urlImg, Long id) {
        userInfoRepo.changeImage(urlImg, id);
    }

    @Override
    public void deleteImage(Long id) {
        userInfoRepo.deleteImage("https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg", id);
    }
}
