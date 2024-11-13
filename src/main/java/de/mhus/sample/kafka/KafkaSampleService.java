package de.mhus.sample.kafka;

import de.mhus.sample.kafka.avro.SampleRecord;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaSampleService {

    private volatile int nextId = 0;

    @Autowired
    private KafkaTemplate<String, SampleRecord> kafkaTemplate;

    public int sendMessage(String text) {
        var message = new SampleRecord(nextId++, text);
        LOGGER.info("Sending message...");
        kafkaTemplate.send("sample-topic", message);
        return message.getId();
    }

    @KafkaListener(topics = "sample-topic", groupId = "sample-group")
    public void listen(SampleRecord message) {
        LOGGER.info("Received message: {}", message);
    }

}
