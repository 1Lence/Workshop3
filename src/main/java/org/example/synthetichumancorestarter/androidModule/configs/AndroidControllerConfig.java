package org.example.synthetichumancorestarter.androidModule.configs;

import org.example.synthetichumancorestarter.androidModule.controller.AndroidCommandController;
import org.example.synthetichumancorestarter.androidModule.controller.exceptionHandler.GlobalAndroidExceptionHandler;
import org.example.synthetichumancorestarter.androidModule.service.CommandExecutorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AndroidControllerConfig {

    @Bean
    @ConditionalOnMissingBean
    public AndroidCommandController androidCommandController(CommandExecutorService commandExecutorService) {
        return new AndroidCommandController(commandExecutorService);
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalAndroidExceptionHandler globalAndroidExceptionHandler() {
        return new GlobalAndroidExceptionHandler();
    }
}