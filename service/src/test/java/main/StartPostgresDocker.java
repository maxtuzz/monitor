package main;

import io.ebean.docker.commands.PostgresConfig;
import io.ebean.docker.commands.PostgresContainer;

/**
 * Start a local postgres docker container.
 * <p>
 * Creates the database and user which we can then use to
 * run locally via Application.main().
 */
public class StartPostgresDocker {

  public static void main(String[] args) {

    PostgresConfig config = new PostgresConfig("11");
    config.setContainerName("pg11");
    config.setPort("7432");
    config.setDbName("ebean_monitor");
    config.setUser("ebean_monitor");
    config.setPassword("test");

    PostgresContainer container = new PostgresContainer(config);
//    container.startWithDropCreate(); // drop and re-create the docker container
    container.start();

    System.out.println("url:" + container.jdbcUrl());
    System.out.println("user:" + config.getUsername());
    System.out.println("pass:" + config.getPassword());
  }
}
