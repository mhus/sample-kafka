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
public class ListRestController {

    @Autowired
    private FirstKafkaSampleService kafkaSampleService;

    public ListRestController() {
        LOGGER.info("Creating List Rest Controller..");
    }

    @GetMapping("/list")
    public String producerAvroMessage() {
        var list = kafkaSampleService.getLastMessages();
        return list.toString();
    }

}
