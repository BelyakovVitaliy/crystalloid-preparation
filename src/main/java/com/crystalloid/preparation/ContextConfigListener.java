package com.crystalloid.preparation;

import com.crystalloid.preparation.api.EndpointConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ContextConfigListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new EndpointConfig(),
                new AppConfig()
        );
    }
}
