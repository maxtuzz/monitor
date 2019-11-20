package org.ebean.monitor.api;

import java.util.List;

public class ListResponse<T> {

  private final List<T> list;

  public ListResponse(List<T> list) {
    this.list = list;
  }

  public List<T> getList() {
    return list;
  }

  @Override
  public String toString() {
    return "ListResponse{" +
      "list=" + list +
      '}';
  }
}
