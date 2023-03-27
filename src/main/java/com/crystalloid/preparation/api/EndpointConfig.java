package com.crystalloid.preparation.api;

import com.google.api.control.ServiceManagementConfigFilter;
import com.google.api.control.extensions.appengine.GoogleAppEngineControlFilter;
import com.google.api.server.spi.guice.EndpointsModule;
import com.google.common.collect.ImmutableList;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class EndpointConfig extends EndpointsModule {
    @Override
    public void configureServlets() {
        super.configureServlets();

        bind(ServiceManagementConfigFilter.class).in(Singleton.class);
        filter("/_ah/api/*").through(ServiceManagementConfigFilter.class);

        Map<String, String> apiController = new HashMap<>();
        apiController.put("endpoints.projectId", "crystalloids-preparation");
        apiController.put("endpoints.serviceName", "crystalloids-preparation.appspot.com");

        bind(GoogleAppEngineControlFilter.class).in(Singleton.class);
        filter("/_ah/api/*").through(GoogleAppEngineControlFilter.class, apiController);

        configureEndpoints("/_ah/api/*", ImmutableList.of(GreetingController.class, UserController.class));
    }
}
