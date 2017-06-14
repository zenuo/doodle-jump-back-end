package yz.doodlejump;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import yz.doodlejump.core.RESTConfiguration;
import yz.doodlejump.core.Data;
import yz.doodlejump.core.SessionManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * 主类
 */
public class Main {
    private static String BASE_URI = null;

    private static HttpServer startServer() {
        final ResourceConfig rc = new RESTConfiguration();
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        //检测参数
        if (args.length != 2) {
            System.err.println("Usage:\n" + "java -jar doodle-jump-back-end-1.0.jar IP PORT");
            System.exit(1);
        }

        BasicConfigurator.configure();
        InputStream inputStream = Main.class.getResourceAsStream("/resources/log4j2.properties");
        org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
        PropertyConfigurator.configure(inputStream);
        inputStream.close();

        SessionManager.init();

        Data.init();

        BASE_URI = "http://" + args[0] + ":" + args[1];
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%s/application.wadl\nHit Enter to stop it...", BASE_URI));

        System.in.read();
        server.shutdownNow();
    }
}
