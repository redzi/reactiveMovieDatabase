package com.redzi.movie.database.redis;

import reactor.core.publisher.Mono;

public abstract class CachedRepository<T extends KeyContainer,R>
{
    protected final GetCachedOrLoad<R> cachedOrLoad;

    public CachedRepository(GetCachedOrLoad<R> cachedOrLoad)
    {
        this.cachedOrLoad = cachedOrLoad;
    }

    public Mono<R> getFromCacheOrLive(T keyContainer)
    {
        return cachedOrLoad.getCachedOrLoad(keyContainer.getKey(), getRealData(keyContainer));
    }

    protected abstract Mono<? extends R> getRealData(T keyContainer);
}
