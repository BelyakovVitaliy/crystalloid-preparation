package com.crystalloid.preparation.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(
        name = "greeting",
        version = "v1",
        description = "Greeting"
)
public class GreetingController {

    @ApiMethod(name = "greeting",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Response greeting() {
        return Response.builder().content("Hello, I am alive").build();
    }
}
