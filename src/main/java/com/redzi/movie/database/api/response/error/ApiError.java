package com.redzi.movie.database.api.response.error;

import com.redzi.movie.database.controller.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError
{
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private ErrorCode errorCode;
    private String message;
}
