package org.syntetic.synteticservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.syntetic.synteticservice.service.UsageExampleLogsService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloAndroidController {
    private final UsageExampleLogsService usageExampleLogsService;

    @GetMapping
    public String logTestController() {
        usageExampleLogsService.veryHardToCountConsoleLog(256, 256);
        usageExampleLogsService.veryHardToCountKafkaLog(512, 512);
        log.info("=============Logs example=====================");
        return "Hello Android";
    }
}