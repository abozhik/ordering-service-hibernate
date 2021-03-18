package abozhik.service;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerShared extends PostgreSQLContainer<PostgreSQLContainerShared> {
    private static final String IMAGE_VERSION = "postgres";
    private static PostgreSQLContainerShared container;

    private PostgreSQLContainerShared() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLContainerShared getInstance() {
        if (container == null) {
            container = new PostgreSQLContainerShared();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        flywayMigrations(container.getJdbcUrl(), container.getUsername(), container.getPassword());
    }

    @Override
    public void stop() {
    }

    public void flywayMigrations(String url, String user, String password) {
        var flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
    }
}