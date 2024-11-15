package de.mhus.sample.kafka.rest;

import de.mhus.sample.kafka.first.FirstKafkaSampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class SampleRestController {

    @Autowired
    private FirstKafkaSampleService kafkaSampleService;

    public SampleRestController() {
        LOGGER.info("Creating Sample Rest Controller..");
    }

    @GetMapping("/sample/{key}/{message}")
    public String producerAvroMessage(
            @PathVariable String key,
            @PathVariable String message,
            @RequestParam(name="errorRate",required = false,defaultValue = "0") int errorRate
            ) {
        LOGGER.info("Generating sample message for key {}.", key);
        var id = kafkaSampleService.sendMessage(key, errorRate, message);
        return "ok " + id;
    }

}
