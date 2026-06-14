package com.project.eduardo.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
public class StandardError{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
