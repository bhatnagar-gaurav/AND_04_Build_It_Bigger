package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.java.joke.lib.Joke_Provider;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that provides random joke for every call */
    @ApiMethod(name = "retrieveJokeFromRobustBackend")
    public MyBean retrieveJokeFromRobustBackend() {
        MyBean response = new MyBean();
        Joke_Provider jokeProvider = new Joke_Provider();
        response.setData(jokeProvider.getRandomJoke());

        return response;
    }

}
