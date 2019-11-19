package org.ebean.monitor.web;

import io.dinject.controller.Controller;
import io.dinject.controller.Get;
import io.dinject.controller.Path;
import io.dinject.controller.Produces;

@Controller
@Path("/")
class HomeController {

  @Get
  @Produces("text/plain")
  String ack() {
    return "ok";
  }
}
