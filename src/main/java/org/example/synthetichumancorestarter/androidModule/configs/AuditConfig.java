package org.example.synthetichumancorestarter.androidModule.configs;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.synthetichumancorestarter.aspects.dto.LogData;
import org.example.synthetichumancorestarter.aspects.logging.AuditAspect;
import org.example.synthetichumancorestarter.aspects.logging.ConsoleLoggingAudit;
import org.example.synthetichumancorestarter.aspects.logging.KafkaLogProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuditConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    @ConditionalOnMissingBean
    public ProducerFactory<String, LogData> producerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                true);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @ConditionalOnMissingBean
    public KafkaTemplate<String, LogData> kafkaTemplate(
            ProducerFactory<String, LogData> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public KafkaLogProducer kafkaLogProducer(KafkaTemplate<String, LogData> kafkaTemplate) {
        return new KafkaLogProducer(kafkaTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleLoggingAudit consoleLoggingAudit(KafkaLogProducer kafkaLogProducer) {
        return new ConsoleLoggingAudit(kafkaLogProducer);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditAspect auditAspect(ConsoleLoggingAudit consoleLoggingAudit) {
        return new AuditAspect(consoleLoggingAudit);
    }
}