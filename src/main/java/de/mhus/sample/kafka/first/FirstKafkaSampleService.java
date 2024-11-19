package de.mhus.sample.kafka.first;

import de.mhus.sample.kafka.avro.SampleRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FirstKafkaSampleService {

    private volatile int nextId = 0;

    @Autowired
    private FirstSampleTopicProducer sampleTopicProducer;

    @Autowired
    FirstKafkaFactory firstKafkaFactory;

//    @Autowired
//    private KafkaAdmin firstKafkaAdmin;

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

    public List<SampleRecord> getLastMessages() {

        TopicPartition topicPartition = new TopicPartition(FirstKafkaFactory.SAMPLE_TOPIC_ID, 0);
        List<TopicPartition> partitions = Arrays.asList(topicPartition);

        KafkaConsumer<String, SampleRecord> consumer = new KafkaConsumer<>(firstKafkaFactory.createConsumerProperties());
        consumer.assign(partitions);
        consumer.seekToBeginning(partitions);
        ConsumerRecords<String, SampleRecord> records = consumer.poll(Duration.ofMinutes(1));
        List<SampleRecord> messages = records.records(topicPartition).stream().map(record -> record.value()).toList();
        return messages;

    }

}
