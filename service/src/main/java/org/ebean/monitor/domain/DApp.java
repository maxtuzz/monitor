package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The "Application" metrics relate to.
 */
@Cache(nearCache = true, naturalKey = "name")
@Entity
@Table(name = "metric_app")
public class DApp extends BaseDomain {

  @Column(unique = true, nullable = false, length = 200)
  private String name;

  public DApp(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
