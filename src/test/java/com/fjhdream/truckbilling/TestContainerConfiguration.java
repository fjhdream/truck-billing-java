package com.fjhdream.truckbilling;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@org.springframework.boot.test.context.TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfiguration {

    @Bean
    @ServiceConnection
    @RestartScope
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"))
                .withUsername("testUser")
                .withPassword("testSecret")
                .withDatabaseName("testDatabase");
    }
}
