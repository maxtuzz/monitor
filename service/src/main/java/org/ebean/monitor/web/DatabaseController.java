package org.ebean.monitor.web;

import io.dinject.controller.Controller;
import io.dinject.controller.Get;
import io.dinject.controller.Path;
import org.ebean.monitor.api.AppDatabase;
import org.ebean.monitor.api.ListResponse;
import org.ebean.monitor.domain.DDatabase;

@Controller
@Path("/api/database")
class DatabaseController {

  @Get
  ListResponse<AppDatabase> getAll() {
    return new ListResponse<>(DDatabase.find.allDatabase());
  }

}
