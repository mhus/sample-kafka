package de.mhus.sample.kafka.second;

import de.mhus.sample.kafka.avro.SampleRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(
        topics = SecondKafkaFactory.SAMPLE_TOPIC_ID,
        groupId = SecondKafkaFactory.SAMPLE_GROUP_ID,
        containerFactory = SecondKafkaFactory.LISTENER_CONTAINER_FACTORY
)
public class SecondSampleTopicConsumer {

    @Autowired
    private SecondKafkaSampleService kafkaSampleService;

    @KafkaHandler
    public void handleSampleRecord(
            @Payload SampleRecord message,
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       Acknowledgment ack) {
        LOGGER.info("Received key {} message: {}", key, message);
        kafkaSampleService.processMessage(key, message);
        ack.acknowledge();
    }

}
