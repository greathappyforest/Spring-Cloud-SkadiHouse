package subscription;


/**
 * Created by Yule on 12/15/17.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class UserSubscriptionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserSubscriptionServiceApplication.class);
    }
}


