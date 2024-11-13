package de.mhus.sample.kafka;

import de.mhus.sample.kafka.avro.SampleRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class SampleController {

    @Autowired
    private KafkaSampleService kafkaDemoService;

    public SampleController() {
        LOGGER.info("Creating SampleController..");
    }

    @GetMapping("/sample/{key}/{message}")
    public String producerAvroMessage(@PathVariable String key, @PathVariable String message) {
        LOGGER.info("Generating sample message for key {}.", key);
        var id = kafkaDemoService.sendMessage(key, message);
        return "ok " + id;
    }

}
