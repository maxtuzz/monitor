package org.ebean.monitor.web;

import io.dinject.controller.Controller;
import io.dinject.controller.Get;
import io.dinject.controller.Path;
import org.ebean.monitor.api.Env;
import org.ebean.monitor.api.ListResponse;
import org.ebean.monitor.domain.DEnv;

@Controller
@Path("/api/env")
class EnvController {

  @Get
  ListResponse<Env> getAll() {
    return new ListResponse<>(DEnv.find.allEnv());
  }
}
