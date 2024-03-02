package instagram.service;

import instagram.entity.UserInfo;

public interface UserInfoService {
    UserInfo findUserInfoByUserId(Long userId);
    String update(Long id, UserInfo newUserInfo);
    void changeImage(String urlImg, Long id);
    void deleteImage(Long id);
}
