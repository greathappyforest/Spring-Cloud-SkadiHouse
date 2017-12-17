package skadihouse.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DistributedInfo {
    private UserInfo userInfo;
    private HouseInfo houseInfo;
    private String timeStamp;



    public DistributedInfo() {
        //timeStamp
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}
