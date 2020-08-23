package com.redzi.movie.database.service.movie.search.connection;

import com.redzi.movie.database.api.response.ResponseContainer;
import com.redzi.movie.database.service.movie.search.exception.NoDataException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;

@Component
public class EmptyResponseHandler<T extends ResponseContainer> implements BiConsumer<T, SynchronousSink<T>>
{
    @Override
    public void accept(T t, SynchronousSink<T> sink)
    {
        if (t.isResponse())
        {
            sink.next(t);
        }
        sink.error(new NoDataException());
    }
}
