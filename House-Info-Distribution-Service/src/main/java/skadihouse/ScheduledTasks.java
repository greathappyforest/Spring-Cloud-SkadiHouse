package skadihouse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import skadihouse.domain.HouseInfo;
import skadihouse.domain.UserInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() throws IOException {
        log.info("The time is now {}", dateFormat.format(new Date()));
        String userInfoUri ="http://127.0.0.1:9001/userinfo/get";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<UserInfo> userInfoResult = (ArrayList<UserInfo>) restTemplate.getForObject(userInfoUri, Map.class).get("content");
        List<UserInfo> userinfos = mapper.convertValue(userInfoResult, new TypeReference<List<UserInfo>>() { });

        for(UserInfo userinfo : userinfos){
            ArrayList<HouseInfo> searchResult = new ArrayList<HouseInfo>();
            long highPrice = userinfo.getHighPrice();
            long lowPrice =userinfo.getLowPrice();
            List<String> cities =userinfo.getCity();

            for(String city : cities){
                System.out.println("city:"+ city);
                String subscribedUrl = "http://127.0.0.1:9000//houseinfo/get/subscribed/3/"+city+"/"+lowPrice+"/"+highPrice+"?size=100";
                ArrayList<HouseInfo> result = (ArrayList<HouseInfo>) restTemplate.getForObject(subscribedUrl, Map.class).get("content");
                searchResult.addAll(result);
                System.out.println("searchResult:");
                System.out.println(searchResult.size());
            }
            System.out.println("searchResult:");
            System.out.println(searchResult.size());


        }


    }


}