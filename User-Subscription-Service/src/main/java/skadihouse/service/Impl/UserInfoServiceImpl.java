package skadihouse.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skadihouse.domain.UserInfoRepository;
import skadihouse.service.UserInfoService;


@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public void deleteByEmail(String email) {
         userInfoRepository.delete(email);
    }


}
