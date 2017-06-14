package yz.doodlejump.core;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;

public class RESTConfiguration extends ResourceConfig{
    public RESTConfiguration() {
        packages("yz.gpslog.core.service");
        property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
        register(JacksonJsonProvider.class);
    }
}
