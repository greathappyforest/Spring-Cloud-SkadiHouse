package houseInfo;

/**
 * Created by Yule on 12/14/17.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class HouseInfoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseInfoServiceApplication.class);
    }
}

