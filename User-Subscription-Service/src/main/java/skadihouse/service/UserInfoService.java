package skadihouse.service;

import skadihouse.domain.UserInfo;

import java.util.List;

public interface UserInfoService {
    void deleteByEmail(String email);
    List<UserInfo> saveUserInfos(List<UserInfo> userInfos);

}
