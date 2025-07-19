package org.syntetic.synteticservice.kafka;

import lombok.Builder;

@Builder
public record LogData(
        String methodName,
        Object[] args,
        Object result
) {
}