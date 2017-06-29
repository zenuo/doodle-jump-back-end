package yz.doodlejump.core;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class RESTConfiguration extends ResourceConfig {
    public RESTConfiguration() {
        packages("yz.doodlejump.service");
        property(ServerProperties.WADL_FEATURE_DISABLE, true);
        property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
        property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        property(CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 20480);
        register(JacksonJsonProvider.class);
    }
}
