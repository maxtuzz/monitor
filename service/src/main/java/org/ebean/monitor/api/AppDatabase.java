package org.ebean.monitor.api;

public class AppDatabase {

  private final String name;

  public AppDatabase(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
