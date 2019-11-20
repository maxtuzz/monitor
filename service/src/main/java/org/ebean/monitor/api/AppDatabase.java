package org.ebean.monitor.api;

public class AppDatabase {

  private final String app;
  private final String db;

  public AppDatabase(String db, String app) {
    this.app = app;
    this.db = db;
  }

  public String getApp() {
    return app;
  }

  public String getDb() {
    return db;
  }

  @Override
  public String toString() {
    return "AppDatabase{" +
      "app='" + app + '\'' +
      ", db='" + db + '\'' +
      '}';
  }
}
