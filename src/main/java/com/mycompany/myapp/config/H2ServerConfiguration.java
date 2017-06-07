package com.mycompany.myapp.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;
import java.util.logging.Logger;

@Configuration
@Profile("dev") // Only activate this in the "dev" profile
public class H2ServerConfiguration {
    private static final Logger log = Logger.getLogger(H2ServerConfiguration.class.getName());

    // TCP port for remote connections, default 9092
    @Value("${h2.tcp.port:9092}")
    private String h2TcpPort;

    // Web port, default 8082
    @Value("${h2.web.port:8082}")
    private String h2WebPort;

    /**
     * TCP connection to connect with SQL clients to the embedded h2 database.
     *
     * Connect to "jdbc:h2:tcp://localhost:9092/mem:testdb", username "sa", password empty.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        Server tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2TcpPort);
        log.info("H2 TCP URL " + tcpServer.getURL());
        return tcpServer;
    }

    /**
     * Web console for the embedded h2 database.
     *
     * Go to http://localhost:8082 and connect to the database "jdbc:h2:mem:testdb", username "sa", password empty.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", h2WebPort);
        log.info("H2 Web URL: " + webServer.getURL());
        return webServer;
    }
}
