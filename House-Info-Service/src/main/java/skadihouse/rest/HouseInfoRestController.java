package skadihouse.rest;


import skadihouse.domain.HouseInfo;
import skadihouse.domain.HouseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HouseInfoRestController {


    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 50;


    private HouseInfoRepository houseInfoRepository;

    @Autowired
    public HouseInfoRestController(HouseInfoRepository houseInfoRepository){
        this.houseInfoRepository=houseInfoRepository;
    }

    //GET findbycity
    @RequestMapping(value = "/houseinfo/get/city/{city}",method = RequestMethod.GET)
    public Page<HouseInfo> findByCity(
            @PathVariable String city, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findByCity(city, pageable);
    }
    //GET findbyzip
    @RequestMapping(value = "/houseinfo/get/zip/{zip}",method = RequestMethod.GET)
    public Page<HouseInfo> findByZip(
            @PathVariable String zip, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findByZip(zip, pageable);
    }

    //GET findbyaddress
    @RequestMapping(value = "/houseinfo/get/address/{address}",method = RequestMethod.GET)
    public Page<HouseInfo> findByAddress(
            @PathVariable String address, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findByAddress(address, pageable);
    }

    //GET findbyDaysOnZillow
    @RequestMapping(value = "/houseinfo/get/days/{daysOnZillow}",method = RequestMethod.GET)
    public Page<HouseInfo> findBydaysOnZillow(
            @PathVariable Integer daysOnZillow, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findByDaysOnZillowLessThanEqualOrderByDaysOnZillow( daysOnZillow, pageable);
    }

    //GET findBySellPriceLessThanEqualOrderBySellPrice
    @RequestMapping(value = "/houseinfo/get/sellprice/{sellPrice}",method = RequestMethod.GET)
    public Page<HouseInfo> findBySellPrice(
            @PathVariable Integer sellPrice, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findBySellPriceLessThanEqualOrderBySellPrice( sellPrice, pageable);
    }
    //GET findbysellprice
    @RequestMapping(value = "/houseinfo/get/sellprice/{startPrice}/{endPrice}",method = RequestMethod.GET)
    public Page<HouseInfo> findBySellPrice(
            @PathVariable Integer startPrice, @PathVariable Integer endPrice, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findBySellPriceBetweenOrderBySellPrice(startPrice,endPrice, pageable);
    }
    //GET findByCityAndSellPriceBetweenAndDaysOnZillowOrderByDaysOnZillow
    @RequestMapping(value = "/houseinfo/get/subscribed/{daysOnZillow}/{city}/{startPrice}/{endPrice}",method = RequestMethod.GET)
    public Page<HouseInfo> subscribedSearch(
            @PathVariable String city,@PathVariable Integer daysOnZillow,  @PathVariable Integer startPrice, @PathVariable Integer endPrice, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.houseInfoRepository.findByDaysOnZillowLessThanEqualAndCityAndSellPriceBetweenOrderByDaysOnZillow( daysOnZillow,city,startPrice,endPrice, pageable);
    }


}
