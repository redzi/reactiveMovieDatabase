# Backend service for a movie database

##

The application is intended as a backend service for a future frontend app.

## Technology

This is a reactive Spring boot application with a non-blocking stack that utilize among other:
* Java 14
* Spring boot 2.3.2
* RSocket (an overkill here as the service does not stream anything for now - used just for learning purposes)
* Redis - used for caching result
* Vavr
* lombok

## How to use

The app optionaly requires a running instance of Redis (spring.redis.host, spring.redis.port).
If redis is not available it handles it but churns out a lot of warning in the logs.

The app is integrated with the OMDB downline service.
It requires an api key for the OMDB movie database which should be set under movie.service.apiKey.
The key could be obtained for OMDB for free (for up to 1000 request per day).

