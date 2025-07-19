package org.example.synthetichumancorestarter.aspects.logging;

import org.example.synthetichumancorestarter.aspects.dto.AuditDto;
import org.example.synthetichumancorestarter.aspects.dto.LogData;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class KafkaLogProducer {
    private final KafkaTemplate<String, LogData> kafkaTemplate;

    public KafkaLogProducer(KafkaTemplate<String, LogData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLogs(AuditDto auditDto, LogData logData) {
        if(!checkCorrectness(auditDto)){
            throw new RuntimeException("You haven't specified a topic in the annotation WeylandWatchingYou");
        }

        kafkaTemplate.send(auditDto.topic(), logData);
    }

    private boolean checkCorrectness(AuditDto auditDto){
        return !auditDto.topic().isBlank();
    }
}