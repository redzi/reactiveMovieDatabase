package com.redzi.movie.database.controller;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.api.response.error.ApiError;
import com.redzi.movie.database.controller.handler.FrontControllerErrorHandler;
import com.redzi.movie.database.service.MovieRatingService;
import com.redzi.movie.database.api.response.SearchDetails;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
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

    @MessageMapping("general")
    public Flux<SearchDetails> getGeneralPaginatedInfo(Mono<InitialPaginatedInfoRequest> infoRequest)
    {
        return infoRequest
                .log(LOG_MESSAGE_IN)
                .flatMapMany(ratingService::fetchGeneralPaginatedInfo)
                .log(LOG_MESSAGE_OUT);
    }

    @MessageMapping("specific")
    public Mono<DetailedInfoResponse> getSpecificInfo(Mono<DetailedInfoRequest> specificRequest)
    {
        return specificRequest
                .log(LOG_MESSAGE_IN)
                .flatMap(ratingService::fetchSpecifics)
                .log(LOG_MESSAGE_OUT);
    }

    @MessageExceptionHandler
    public Mono<ApiError> handleException(Exception e)
    {
        return errorHandler.handleException(e);
    }
}
