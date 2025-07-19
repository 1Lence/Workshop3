package org.example.synthetichumancorestarter.aspects.logging;

import org.example.synthetichumancorestarter.annotations.model.LoggingType;
import org.example.synthetichumancorestarter.aspects.dto.AuditDto;
import org.example.synthetichumancorestarter.aspects.dto.LogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsoleLoggingAudit {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleLoggingAudit.class);
    private final KafkaLogProducer kafkaLogProducer;

    public ConsoleLoggingAudit(KafkaLogProducer kafkaLogProducer) {
        this.kafkaLogProducer = kafkaLogProducer;
    }

    public void sendLogs(AuditDto auditDto, LogData logData) {
        if (auditDto.loggingType().equals(LoggingType.CONSOLE_LOG)) {
            LOGGER.info("Method Name: {}; Method Args {}; Returning data: {}", logData.methodName(), logData.args(), logData.result());
        }else {
            kafkaLogProducer.sendLogs(auditDto, logData);
        }
    }
}