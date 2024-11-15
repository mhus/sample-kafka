package de.mhus.sample.kafka.first;

import de.mhus.sample.kafka.avro.SampleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FirstSampleTopicProducer {

    @Autowired
    private FirstKafkaFactory myKafkaFactory;
    private KafkaTemplate<String, SampleRecord> sampleRecordTemplate;

    public KafkaTemplate<String, SampleRecord> getSampleRecordTemplate() {
        if (sampleRecordTemplate == null)
            sampleRecordTemplate = new KafkaTemplate<>(myKafkaFactory.createProducerFactory());
        return sampleRecordTemplate;
    }

    public void send(String key, SampleRecord message) {
        getSampleRecordTemplate().send(FirstKafkaFactory.SAMPLE_TOPIC_ID, key, message);
    }

}
