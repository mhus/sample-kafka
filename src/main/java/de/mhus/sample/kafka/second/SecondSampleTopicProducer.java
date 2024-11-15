package de.mhus.sample.kafka.second;

import de.mhus.sample.kafka.avro.SampleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SecondSampleTopicProducer {

    @Autowired
    private SecondKafkaFactory myKafkaFactory;

    public KafkaTemplate<String, SampleRecord> kafkaTemplate() {
        return new KafkaTemplate<>(myKafkaFactory.createProducerFactory());
    }

    public void send(String key, SampleRecord message) {
        kafkaTemplate().send(SecondKafkaFactory.SAMPLE_TOPIC_ID, key, message);
    }

}
