package yz.doodlejump;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import yz.doodlejump.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * 主类
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
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
        MessageManager.init();
        TeamManager.init();

        Data.init();

        final URI BASE_URI = URI.create(String.format("http://%s:%s/", args[0], args[1]));

        ResourceConfig resourceConfig = new RESTConfiguration();

        final Channel server = NettyHttpContainerProvider
                .createHttp2Server(BASE_URI,
                        resourceConfig,
                        null
                );

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.close();
            }
        }));

        System.out.println(String.format("Application started. (HTTP/2 enabled!)\nTry out %sapplication.wadl\nStop the application using CTRL+C.", BASE_URI));

        Thread.currentThread().join();
    }
}
