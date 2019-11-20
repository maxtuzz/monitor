package org.ebean.monitor;

import io.dinject.SystemContext;
import io.dinject.controller.WebRoutes;
import io.javalin.Javalin;

import java.util.List;

public class Application {

  public static void main(String[] args) {

    final List<WebRoutes> routes = SystemContext.getBeans(WebRoutes.class);
    create(routes)
      .start(8090);
  }

  /**
   * Start the application with the given port.
   */
  static Javalin start(int port) {
    return create(SystemContext.getBeans(WebRoutes.class))
      .start(port);
  }

  private static Javalin create(List<WebRoutes> routes) {
    final Javalin app = Javalin.create(config -> {
        config.showJavalinBanner = false;
        config.logIfServerNotStarted = true;
      }
    );
    return app.routes(() -> routes.forEach(WebRoutes::registerRoutes));
  }
}
