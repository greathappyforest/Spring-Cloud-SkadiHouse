package userinfo.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import userinfo.domain.UserInfo;
import userinfo.domain.UserInfoRepository;
import userinfo.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
   

    @Override
    public UserInfo deleteByEmail(String email) {
        return null;
    }
}
