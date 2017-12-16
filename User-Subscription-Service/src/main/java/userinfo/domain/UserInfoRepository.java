package subscription.domain;

import houseInfo.domain.HouseInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserInfoRepository extends PagingAndSortingRepository<HouseInfo,String> {


}
