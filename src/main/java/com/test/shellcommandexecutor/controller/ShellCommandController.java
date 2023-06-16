package com.test.shellcommandexecutor.controller;

import com.test.shellcommandexecutor.domain.ShellCommand;
import com.test.shellcommandexecutor.dto.ShellCommandExecutionResponse;
import com.test.shellcommandexecutor.service.ShellCommandExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/commands/shell/api/rest/v1")
public class ShellCommandController {

    private final ShellCommandExecutorService shellCommandExecutorService;

    @Autowired
    public ShellCommandController(ShellCommandExecutorService shellCommandExecutorService) {
        this.shellCommandExecutorService = shellCommandExecutorService;
    }


    @PostMapping(value = "/execute", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ShellCommandExecutionResponse> executeShellCommand(@RequestBody ShellCommand shellCommand) {
        try {
            ShellCommandExecutionResponse shellCommandExecutionResponse = shellCommandExecutorService.executeShellCommand(shellCommand);
            return ResponseEntity.ok(shellCommandExecutionResponse);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute shell command", e);
        }
    }
}
