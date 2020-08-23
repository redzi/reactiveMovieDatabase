package com.redzi.movie.database.controller.handler;

import com.redzi.movie.database.api.response.error.ApiError;
import com.redzi.movie.database.controller.error.ErrorCode;
import com.redzi.movie.database.service.movie.search.exception.NoDataException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Component
public class FrontControllerErrorHandler implements ErrorHandler<ApiError>
{
    @Override
    public Mono<ApiError> handleException(Exception exception)
    {
        return Match(exception).of(
                Case($(instanceOf(NoDataException.class)), createErrorResponse(exception, ErrorCode.NO_DATA_FOUND, HttpStatus.BAD_REQUEST)),
                Case($(), createErrorResponse(exception, ErrorCode.UNKNOWN_SERVER_PROBLEM, HttpStatus.INTERNAL_SERVER_ERROR))
        );
    }

    private Mono<ApiError> createErrorResponse(Exception exception, ErrorCode errorCode, HttpStatus status)
    {
        return Mono.just(new ApiError(LocalDateTime.now(),
                status,
                errorCode,
                exception.getMessage()));
    }
}
