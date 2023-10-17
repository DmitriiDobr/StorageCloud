package com.example.storagecloud.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ApiExceptionRP {

    private String message;

    private Throwable throwable;

    private HttpStatus httpStatus;

    private ZonedDateTime zonedDateTime;
}
