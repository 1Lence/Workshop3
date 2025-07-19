package org.example.synthetichumancorestarter.aspects.dto;

import lombok.Builder;

@Builder
public record LogData(
        String methodName,
        Object[] args,
        Object result
) {
}