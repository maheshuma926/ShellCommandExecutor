package com.test.shellcommandexecutor.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShellCommandExecutionResponse {
    private String result;
    private String error;
    private int exitCode;

}
