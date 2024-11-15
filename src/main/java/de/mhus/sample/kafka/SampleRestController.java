package de.mhus.sample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class SampleRestController {

    @Autowired
    private KafkaSampleService kafkaSampleService;

    public SampleRestController() {
        LOGGER.info("Creating Sample Rest Controller..");
    }

    @GetMapping("/sample/{key}/{message}")
    public String producerAvroMessage(@PathVariable String key, @PathVariable String message) {
        LOGGER.info("Generating sample message for key {}.", key);
        var id = kafkaSampleService.sendMessage(key, message);
        return "ok " + id;
    }

}
