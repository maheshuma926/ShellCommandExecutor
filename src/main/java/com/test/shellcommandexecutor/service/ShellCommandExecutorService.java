package com.test.shellcommandexecutor.service;

import com.test.shellcommandexecutor.domain.ShellCommand;
import com.test.shellcommandexecutor.dto.ShellCommandExecutionResponse;

import java.io.IOException;

public interface ShellCommandExecutorService {
    ShellCommandExecutionResponse executeShellCommand(ShellCommand shellCommand) throws IOException;
}
