package org.ebean.monitor;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import static org.ebean.monitor.ResourceHelp.read;

public class IngestLocal {

  public static void main(String[] args) {

    ingest(read("/examples/oas-0.json"));
  }

  private static void ingest(String bodyA) {
    final HttpResponse httpResponse = Unirest.post("http://localhost:8090/api/ingest")
      .header("Content-Type", "application/json")
      .body(bodyA)
      .asEmpty();

    if (!httpResponse.isSuccess()) {
      throw new IllegalStateException("Failed ingest request " + httpResponse.getStatus());
    }
  }
}
