package com.test.shellcommandexecutor.service.impl;

import com.test.shellcommandexecutor.domain.ExecutionEnvironment;
import com.test.shellcommandexecutor.domain.ShellCommand;
import com.test.shellcommandexecutor.dto.ShellCommandExecutionResponse;
import com.test.shellcommandexecutor.service.ShellCommandExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
@Slf4j
public class ShellCommandExecutorServiceImpl implements ShellCommandExecutorService {

    @Autowired
    ExecutionEnvironment executionEnvironment;

    @Override
    public ShellCommandExecutionResponse executeShellCommand(ShellCommand shellCommand) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        log.debug("shell command: " + shellCommand);
        processBuilder.command("bash", "-c", "cd " + shellCommand.getExecutionEnvironment().getWorkingDirectory() + " && " + shellCommand.getCommand());
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder errorOutput = new StringBuilder();
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            errorOutput.append(errorLine).append("\n");
        }

        try {
            int exitCode = process.waitFor();
            return new ShellCommandExecutionResponse(output.toString(), errorOutput.toString(), exitCode);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Command execution interrupted", e);
        }
    }
}
