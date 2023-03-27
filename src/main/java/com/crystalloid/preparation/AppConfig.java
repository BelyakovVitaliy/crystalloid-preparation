package com.crystalloid.preparation;

import com.crystalloid.preparation.model.UserDetails;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import lombok.SneakyThrows;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class AppConfig extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class.getName());

    @Override
    @SneakyThrows
    protected void configure() {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        Names.bindProperties(binder(), properties);
        LOGGER.warn("props are " + properties.getProperty("spring.cloud.gcp.firestore.project-id"));
        configureDao();
    }

    @SneakyThrows
    private void configureDao() {
        DatastoreOptions options = DatastoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(AppConfig.class.getClassLoader().getResourceAsStream("crystalloids-preparation-9595b87ed4c2.json")))
                .build();
        Datastore datastore = options.getService();
        ObjectifyService.init(new ObjectifyFactory(datastore));
        ObjectifyService.register(UserDetails.class);
    }
}
