package org.ebean.monitor.web;

import io.dinject.controller.Controller;
import io.dinject.controller.Get;
import io.dinject.controller.Path;
import org.ebean.monitor.api.App;
import org.ebean.monitor.api.AppDatabase;
import org.ebean.monitor.api.ListResponse;
import org.ebean.monitor.domain.DApp;

@Controller
@Path("/api/app")
class AppController {

  @Get
  ListResponse<App> getAll() {
    return new ListResponse<>(DApp.find.allApp());
  }

  @Get("/db")
  ListResponse<AppDatabase> getAllDatabases() {
    return new ListResponse<>(DApp.find.allAppDatabase());
  }
}
