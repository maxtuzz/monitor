package org.ebean.monitor;

import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.Test;

import static org.ebean.monitor.ResourceHelp.read;

public class IngestControllerTest {

  @Test
  public void ingest() throws InterruptedException {

    final String bodyA = read("/request/req-3a.json");
    final String bodyB = read("/request/req-3b.json");
    final String bodyC = read("/request/req-3c.json");

    Javalin app = Application.start(9091);
    System.out.println("----------------");
    try {
      System.out.println("---------------- ingesting");
      ingest(bodyA);
      ingest(bodyB);
      ingest(bodyC);

      System.out.println("---------------- sleeping");
      // allow queue consumer to process
      Thread.sleep(2000);

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
      throw new IllegalStateException("Failed ingest request "+httpResponse.getStatus());
    }
  }
}
