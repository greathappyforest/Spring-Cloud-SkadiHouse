package userinfo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import houseInfo.domain.HouseInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PushedHouseInfo {
    private HouseInfo houseInfo;
    private boolean pushed;
    private Date date;
}
