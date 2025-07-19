package org.example.synthetichumancorestarter.androidModule.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.synthetichumancorestarter.androidModule.dto.model.CommandPriority;

@Builder
public record AndroidCommand(
        @Size(max = 1000, message = "Description cannot be more than 1000 characters")
        String description,
        CommandPriority priority,
        @Size(max = 100, message = "Author cannot be more than 100 characters")
        String author,
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?(Z|[+-]\\d{2}:\\d{2})?)?$",
                message = "Date must be in ISO-8601 format (YYYY-MM-DD or YYYY-MM-DDTHH:MM:SSZ)"
        )
        String time
) {}