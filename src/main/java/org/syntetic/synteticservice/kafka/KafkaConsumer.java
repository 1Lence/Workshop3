package org.syntetic.synteticservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "android-logs", groupId = "my-group")
    public void listen(LogData logData) {
        log.info("Message received from Kafka: Method name: {}; Args: {}; Results: {};",
                logData.methodName(), logData.args(), logData.result());
    }
}