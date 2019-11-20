package org.ebean.monitor;

import io.javalin.Javalin;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.ebean.monitor.api.App;
import org.ebean.monitor.api.AppDatabase;
import org.ebean.monitor.api.Env;
import org.ebean.monitor.api.ListResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ebean.monitor.ResourceHelp.read;

public class IngestControllerTest {

  @Test
  public void ingest() throws InterruptedException {

    final String bodyA = read("/request/req-3a.json");
    final String bodyB = read("/request/req-3b.json");
    final String bodyC = read("/request/req-3c.json");

    Javalin app = Application.start(9091);
    try {
      System.out.println("---------------- ingesting");
      ingest(bodyA);
      ingest(bodyB);
      ingest(bodyC);

      System.out.println("---------------- sleeping");
      // allow queue consumer to process
      Thread.sleep(500);

      final ListResponse<Env> envs = getEnvironments();
      assertThat(envs.getList())
        .extracting(Env::getName)
        .contains("dev1");

      final ListResponse<App> apps = getApps();
      assertThat(apps.getList())
        .extracting(App::getName)
        .contains("int1");

      final ListResponse<AppDatabase> dbs = getDbs();
      assertThat(dbs.getList())
        .extracting(AppDatabase::getName)
        .contains("int1.db");

    } finally {
      app.stop();
    }
  }

  private void ingest(String bodyA) {
    final HttpResponse httpResponse = Unirest.post("http://localhost:9091/api/ingest")
      .header("Content-Type", "application/json")
      .body(bodyA)
      .asEmpty();

    if (!httpResponse.isSuccess()) {
      throw new IllegalStateException("Failed ingest request " + httpResponse.getStatus());
    }
  }

  private ListResponse<Env> getEnvironments() {
    return Unirest.get("http://localhost:9091/api/env")
      .header("Content-Type", "application/json")
      .asObject(new GenericType<ListResponse<Env>>() {})
      .getBody();
  }

  private ListResponse<App> getApps() {
    return Unirest.get("http://localhost:9091/api/app")
      .header("Content-Type", "application/json")
      .asObject(new GenericType<ListResponse<App>>() {})
      .getBody();
  }

  private ListResponse<AppDatabase> getDbs() {
    return Unirest.get("http://localhost:9091/api/database")
      .header("Content-Type", "application/json")
      .asObject(new GenericType<ListResponse<AppDatabase>>() {})
      .getBody();
  }
}
