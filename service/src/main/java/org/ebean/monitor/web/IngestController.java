package org.ebean.monitor.web;

import io.dinject.controller.Controller;
import io.dinject.controller.Get;
import io.dinject.controller.Path;
import io.dinject.controller.Post;
import io.dinject.controller.Produces;
import org.ebean.monitor.api.MetricRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@Path("/api/ingest")
class IngestController {

  private static final Logger log = LoggerFactory.getLogger(IngestController.class);

  @Get
  @Produces("text/plain")
  String ack() {
    return "ok";
  }

  @Post
  void ingest(MetricRequest data) {

    log.info("Got data "+data);
  }
}
