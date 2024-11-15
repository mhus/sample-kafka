package de.mhus.sample.kafka.first;

import de.mhus.sample.kafka.avro.SampleRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FirstKafkaSampleService {

    private volatile int nextId = 0;

    @Autowired
    private FirstSampleTopicProducer sampleTopicProducer;

    public int sendMessage(String key, int errorRate, String text) {
        var message = new SampleRecord(nextId++, errorRate, text);
        LOGGER.info("Sending message...");
        sampleTopicProducer.send(key, message);
        return message.getId();
    }

    public void processMessage(String key, SampleRecord message) {
        LOGGER.info("Process key {} message: {}", key, message);
        if (Math.random() * 100 < message.getErrorRate()) {
            LOGGER.info("Simulating error...");
            throw new RuntimeException("Simulated error");
        }
    }

}
