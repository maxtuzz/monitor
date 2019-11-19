package org.ebean.monitor;

import io.dinject.SystemContext;
import io.dinject.controller.WebRoutes;
import io.javalin.Javalin;

import java.util.List;

public class Application {

  public static void main(String[] args) {

    final Javalin app = Javalin.create(config -> {
        config.showJavalinBanner = false;
        config.logIfServerNotStarted = true;
      }
    );

    final List<WebRoutes> routes = SystemContext.getBeans(WebRoutes.class);
    app.routes(() -> routes.forEach(WebRoutes::registerRoutes))
      .start(8090);
  }
}
