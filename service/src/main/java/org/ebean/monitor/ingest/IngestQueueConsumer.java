package org.ebean.monitor.ingest;

import io.ebean.DB;
import org.ebean.monitor.api.MetricRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

/**
 * Reads the metrics data from the queue and ingests it into the DB.
 */
@Singleton
public class IngestQueueConsumer {

  private static final Logger log = LoggerFactory.getLogger(IngestQueueConsumer.class);

  private static final long delayMillis = 200;

  private final IngestQueue queue;

  private final IngestMessage ingestMessage;

  IngestQueueConsumer(IngestQueue queue, IngestMessage ingestMessage) {
    this.queue = queue;
    this.ingestMessage = ingestMessage;
  }

  @PostConstruct
  public void start() {
    log.debug("starting ingest queue consumer");
    DB.getBackgroundExecutor().executePeriodically(() -> {

      log.trace("polling ...");
      MetricRequest data;
      while ((data = queue.poll()) != null) {
        ingestRequest(data);
      }

    }, delayMillis, TimeUnit.MILLISECONDS);
  }

  private void ingestRequest(MetricRequest data) {
    log.debug("ingesting request");
    try {
      ingestMessage.ingest(data);
    } catch (Exception e) {
      log.error("Error ingesting request", e);
      //TODO: put onto a retry queue
    }
  }
}
