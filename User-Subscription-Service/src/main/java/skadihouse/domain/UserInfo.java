package skadihouse.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "subscribed_userInfo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserInfo {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private long lowPrice;
    private long highPrice;
    private List<String> city;
//    private long days;
//    private long zip;
    private List<PushedHouseInfo> pushedHouseInfo;


}
