package org.example.synthetichumancorestarter.androidModule.configs;

import org.example.synthetichumancorestarter.androidModule.service.CommandExecutorService;
import org.example.synthetichumancorestarter.metrics.service.AndroidCompletedTasksCounter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandExecutorServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public CommandExecutorService commandExecutorService(AndroidCompletedTasksCounter completedTasksCounter){
        return new CommandExecutorService(completedTasksCounter);
    }
}