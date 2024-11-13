package de.mhus.sample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Slf4j
public class SampleKafkaApplication {

        public static void main(String[] args) {
            LOGGER.info("Starting application");
            SpringApplication.run(SampleKafkaApplication.class, args);
        }

}
