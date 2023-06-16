package com.test.shellcommandexecutor.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShellCommand {
    private String command;
    private List<CommandOptions> options;
    private List<CommandArguments> arguments;

    private ExecutionEnvironment executionEnvironment;
}
