package com.redzi.movie.database.service.movie.search.exception;

public class NoDataException extends RuntimeException
{
    private static final String DEFAULT_MESSAGE = "No data returned.";

    public NoDataException()
    {
        super(DEFAULT_MESSAGE);
    }

    public NoDataException(String message)
    {
        super(message);
    }
}
