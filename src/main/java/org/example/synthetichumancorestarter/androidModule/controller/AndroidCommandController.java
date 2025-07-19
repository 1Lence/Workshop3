package org.example.synthetichumancorestarter.androidModule.controller;

import org.example.synthetichumancorestarter.androidModule.dto.AndroidCommand;
import org.example.synthetichumancorestarter.androidModule.service.CommandExecutorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/android-command")
public class AndroidCommandController {
    private final CommandExecutorService commandExecutorService;

    public AndroidCommandController(CommandExecutorService commandExecutorService) {
        this.commandExecutorService = commandExecutorService;
    }

    @PostMapping
    public void addCommand(@Validated @RequestBody AndroidCommand command) {
        commandExecutorService.executeCommand(command);
    }
}