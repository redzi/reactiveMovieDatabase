package com.redzi.movie.database.controller.handler;

import com.redzi.movie.database.api.response.error.ApiError;
import reactor.core.publisher.Mono;

public interface ErrorHandler<T extends ApiError>
{
    Mono<T> handleException(Exception exception);
}
