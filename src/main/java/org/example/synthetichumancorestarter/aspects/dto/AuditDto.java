package org.example.synthetichumancorestarter.aspects.dto;

import org.example.synthetichumancorestarter.annotations.model.LoggingType;
import lombok.Builder;

@Builder
public record AuditDto(
        LoggingType loggingType,
        String topic
) {
}