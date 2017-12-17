package skadihouse.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skadihouse.domain.UserInfo;
import skadihouse.domain.UserInfoRepository;
import skadihouse.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public void deleteByEmail(String email) {
         userInfoRepository.delete(email);
    }

    @Override
    public List<UserInfo> saveUserInfos(List<UserInfo> userInfos) {
        return (ArrayList<UserInfo>)userInfoRepository.save(userInfos);
    }

}
