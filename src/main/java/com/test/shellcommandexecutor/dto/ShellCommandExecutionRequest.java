package com.test.shellcommandexecutor.dto;

import com.test.shellcommandexecutor.domain.ShellCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ShellCommandExecutionRequest {
    private ShellCommand shellCommand;

}
