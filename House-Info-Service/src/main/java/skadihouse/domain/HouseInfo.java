package skadihouse.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "zillow_house_info")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class HouseInfo {
    @Id
    private String _id;
    private String url;

    private String houseType;
    private Integer size;
    private Integer lot;
    private Integer parking;
    private Integer yearBuild;
    private Integer daysOnZillow;
    private String heating;
    private String cooling;
    private Integer badrooms;
    private Integer bathrooms;

    private String address;
    private String zip;
    private String city;
    private String state;


    private Integer sellPrice;
    private Integer pricePerSqft;
    private String description;

//    @JsonCreator
//    public HouseInfo(@JsonProperty("daysOnZillow") String daysOnZillow){
//        this.daysOnZillow = Integer.parseInt(daysOnZillow.replaceAll("[^\\d.]", ""));
//    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof HouseInfo)) return false;
        if(((HouseInfo)obj).url == url) return true;
        return false;
    }
    @Override
    public int hashCode() {
        return (url!=null?url.hashCode():1)*31+yearBuild;
    }

}
