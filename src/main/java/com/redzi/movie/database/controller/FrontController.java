package com.redzi.movie.database.controller;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.InitialPaginatedInfoResponse;
import com.redzi.movie.database.api.response.error.ApiError;
import com.redzi.movie.database.controller.handler.FrontControllerErrorHandler;
import com.redzi.movie.database.service.MovieRatingService;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class FrontController
{
    private static final String LOG_MESSAGE_IN = "Received a request:";
    private static final String LOG_MESSAGE_OUT = "Returns a reponse:";
    private final MovieRatingService ratingService;
    private final FrontControllerErrorHandler errorHandler;

    public FrontController(MovieRatingService ratingService,
                           FrontControllerErrorHandler errorHandler)
    {
        this.ratingService = ratingService;
        this.errorHandler = errorHandler;
    }

    @MessageMapping("rating")
    public Mono<InitialPaginatedInfoResponse> getRating(Mono<InitialPaginatedInfoRequest> movieRequest)
    {
        return movieRequest
                .log(LOG_MESSAGE_IN)
                .flatMap(ratingService::fetchRating)
                .log(LOG_MESSAGE_OUT);
    }

    @MessageExceptionHandler
    public Mono<ApiError> handleException(Exception e)
    {
        return errorHandler.handleException(e);
    }
}
