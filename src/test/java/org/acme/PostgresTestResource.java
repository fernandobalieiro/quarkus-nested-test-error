package org.acme;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

import static org.testcontainers.containers.BindMode.READ_ONLY;

public class PostgresTestResource implements QuarkusTestResourceLifecycleManager {

    static PostgreSQLContainer<?> db =
        new PostgreSQLContainer<>("postgres:14.7")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withClasspathResourceMapping("init.sql",
                "/docker-entrypoint-initdb.d/init.sql",
                READ_ONLY);

    @Override
    public Map<String, String> start() {
        db.start();

        return Collections.singletonMap("quarkus.datasource.jdbc.url",
            String.format("jdbc:postgresql://localhost:%d/test", db.getFirstMappedPort()));
    }

    @Override
    public void stop() {
        db.stop();
    }
}
