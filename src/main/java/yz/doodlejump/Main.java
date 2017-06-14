package yz.doodlejump;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import yz.doodlejump.core.Configuration;

import java.net.URI;

/**
 * 主类
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    private static String BASE_URI = null;

    /**
     * 启动Grizzly HTTP server。
     *
     * @return Grizzly HTTP server实例
     */
    private static HttpServer startServer() {
        final ResourceConfig rc = new Configuration();
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }



    public static void main(String[] args) {

    }
}
