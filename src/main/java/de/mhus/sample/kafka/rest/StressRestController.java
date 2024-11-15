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
public class StressRestController {

    @Autowired
    private FirstKafkaSampleService kafkaSampleService;

    public StressRestController() {
        LOGGER.info("Creating StressController..");
    }

    @GetMapping("/stress/{keys}/{message}")
    public String producerAvroMessage(
            @PathVariable final String keys,
            @PathVariable final String message,
            @RequestParam(name="amount",required = false,defaultValue = "10000") final int amount,
            @RequestParam(name="errorRate",required = false,defaultValue = "0") final int errorRate
    ) {
        LOGGER.info("Generating sample messages for keys {}.", keys);
        for (int i = 0; i < amount; i++) {
            for (String key : keys.split(",")) {
                kafkaSampleService.sendMessage(key, errorRate, message + " " + i);
            }
        }

        return "ok";
    }

}
