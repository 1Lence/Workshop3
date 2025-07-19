package org.syntetic.synteticservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.synthetichumancorestarter.annotations.WeylandWatchingYou;
import org.example.synthetichumancorestarter.annotations.model.LoggingType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsageExampleLogsService {

    @WeylandWatchingYou(type = LoggingType.CONSOLE_LOG, topic = "")
    public Integer veryHardToCountConsoleLog(Integer firstNumber, Integer secondNumber) {
        log.info("=============Logs example=====================");
        return firstNumber + secondNumber;
    }

    @WeylandWatchingYou(type = LoggingType.CONSOLE_LOG, topic = "")
    public List<Integer> veryHardToCountKafkaLog(Integer firstNumber, Integer secondNumber) {
        return List.of(firstNumber, secondNumber);
    }
}