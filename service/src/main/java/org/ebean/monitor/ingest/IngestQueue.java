package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricRequest;

import javax.inject.Singleton;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Singleton
public class IngestQueue {

  private final BlockingQueue<MetricRequest> queue = new LinkedBlockingQueue<>();

  public void put(MetricRequest request) {
    queue.add(request);
  }

  public MetricRequest poll() {
    return queue.poll();
  }
}
