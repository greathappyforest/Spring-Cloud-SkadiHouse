package skadihouse.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;



public interface HouseInfoRepository extends PagingAndSortingRepository<HouseInfo,String>{
   // Page<HouseInfo> findByUrl(@Param("url") String url, Pageable pageable);
    Page<HouseInfo> findByCity(@Param("city") String city, Pageable pageable);
    Page<HouseInfo> findByZip(@Param("zip") String zip, Pageable pageable);
    Page<HouseInfo> findByAddress(@Param("address") String address, Pageable pageable);

    Page<HouseInfo> findByDaysOnZillowLessThanEqualOrderByDaysOnZillow(@Param("daysOnZillow") Integer daysOnZillow, Pageable pageable);
    Page<HouseInfo> findBySellPriceBetweenOrderBySellPrice(@Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice, Pageable pageable);
    Page<HouseInfo> findBySellPriceLessThanEqualOrderBySellPrice(@Param("sellPrice") Integer sellPrice, Pageable pageable);


    Page<HouseInfo> findByDaysOnZillowLessThanEqualAndCityAndSellPriceBetweenOrderByDaysOnZillow( @Param("daysOnZillow") Integer daysOnZillow, @Param("city") String city, @Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice, Pageable pageable);

}
