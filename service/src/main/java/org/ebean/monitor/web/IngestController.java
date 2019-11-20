package org.ebean.monitor.web;

import io.dinject.controller.Controller;
import io.dinject.controller.Get;
import io.dinject.controller.Path;
import io.dinject.controller.Post;
import io.dinject.controller.Produces;
import org.ebean.monitor.api.MetricRequest;
import org.ebean.monitor.ingest.IngestQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@Path("/api/ingest")
class IngestController {

  private static final Logger log = LoggerFactory.getLogger(IngestController.class);

  private final IngestQueue queue;

  IngestController(IngestQueue queue) {
    this.queue = queue;
  }

  @Get
  @Produces("text/plain")
  String ack() {
    return "ok";
  }

  /**
   * Ingest the metrics.
   */
  @Post
  void ingest(MetricRequest data) {
    // put it on the queue and ingest into DB in the background
    queue.put(data);
  }
}
