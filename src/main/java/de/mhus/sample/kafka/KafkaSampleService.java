package de.mhus.sample.kafka;

import de.mhus.sample.kafka.avro.SampleRecord;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaSampleService {

    private volatile int nextId = 0;

    @Autowired
    private KafkaTemplate<String, SampleRecord> kafkaTemplate;

    public int sendMessage(String key, String text) {
        var message = new SampleRecord(nextId++, text);
        LOGGER.info("Sending message...");
        kafkaTemplate.send("sample-topic",  key, message);
        return message.getId();
    }

    @KafkaListener(topics = "sample-topic", groupId = "sample-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(SampleRecord message,
                       @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       Acknowledgment ack) {
        LOGGER.info("Received key {} message: {}", key, message);
        if (Math.random() < 0.1) {
            LOGGER.info("Simulating error...");
            throw new RuntimeException("Simulated error");
        }
        ack.acknowledge();
    }

}
