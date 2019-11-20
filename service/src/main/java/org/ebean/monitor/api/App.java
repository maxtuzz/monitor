package org.ebean.monitor.api;

public class App {

  private final String name;

  public App(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "App{" +
      "name='" + name + '\'' +
      '}';
  }
}
