package skadihouse.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skadihouse.domain.UserInfo;
import skadihouse.domain.UserInfoRepository;
import skadihouse.service.UserInfoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserInfoRestController {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 50;


    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoService userInfoService;
    //Get findAll
    @RequestMapping(value = "/userinfo/get",method = RequestMethod.GET)
    public Page<UserInfo> findAll(
           @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.userInfoRepository.findAll(pageable);
    }

    //GET findByEmail
    @RequestMapping(value = "/userinfo/get/email/{email}/end",method = RequestMethod.GET)
    public Page<UserInfo> findByEmail(
            @PathVariable String email, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.userInfoRepository.findByEmail(email, pageable);
    }

    //Post

    @RequestMapping(value = "/userinfo/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserInfo> upload(@RequestBody List<UserInfo> userInfos){
        return this.userInfoService.saveUserInfos(userInfos);
    }


    //Delete
    @RequestMapping(value = "/userinfo/delete/{email}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteByEmail(@PathVariable String email){
         this.userInfoService.deleteByEmail(email);
    }





}
