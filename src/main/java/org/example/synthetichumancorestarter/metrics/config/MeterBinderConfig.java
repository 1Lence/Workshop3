package org.example.synthetichumancorestarter.metrics.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.example.synthetichumancorestarter.androidModule.service.CommandExecutorService;
import org.example.synthetichumancorestarter.metrics.service.AndroidCompletedTasksCounter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeterBinderConfig {

    @Bean
    @ConditionalOnMissingBean
    public AndroidCompletedTasksCounter androidCompletedTasksCounter(MeterRegistry registry) {
        return new AndroidCompletedTasksCounter(registry);
    }

    @Bean
    public MeterBinder meterBinder(CommandExecutorService commandExecutorService) {
        return registry -> {
            Counter.builder("completed_tasks_by_authors_counter").
                    description("Number of completed tasks for each author")
                    .tag("author", "author")
                    .register(registry);

            Gauge.builder("tasks_thread_pool_size", commandExecutorService::getQueueSize)
                    .description("Current Android occupancy (number of tasks in queue)")
                    .register(registry);
        };
    }
}