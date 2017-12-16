package houseInfo.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;



public interface HosueInfoRepository extends PagingAndSortingRepository<HouseInfo,String>{
    Page<HouseInfo> findByUrl(@Param("url") String url, Pageable pageable);
    Page<HouseInfo> findByLocation_City(@Param("city") String city, Pageable pageable);
    Page<HouseInfo> findByLocation_Zip(@Param("zip") String zip, Pageable pageable);
    Page<HouseInfo> findByLocation_Address(@Param("address") String address, Pageable pageable);
    Page<HouseInfo> findByFeatures_DaysOnZillowLessThanEqual(@Param("daysOnZillow") String daysOnZillow, Pageable pageable);

}
