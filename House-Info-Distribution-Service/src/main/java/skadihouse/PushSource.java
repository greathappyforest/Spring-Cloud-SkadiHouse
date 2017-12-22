package skadihouse;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableBinding(Source.class)
@Component
public class PushSource {
    @Autowired
    private MessageChannel output;


    @RequestMapping(value = "/api/distributedinfos", method = RequestMethod.POST)
    public void pushMessage( String jsonString) {
        this.output.send(MessageBuilder.withPayload(jsonString).build());
        log.info("objNode output in distribution: ");
        log.info(jsonString);
    }


}