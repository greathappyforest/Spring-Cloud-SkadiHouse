package userinfo.service;

import userinfo.domain.UserInfo;

public interface UserInfoService {
    UserInfo deleteByEmail(String email);
    
}
