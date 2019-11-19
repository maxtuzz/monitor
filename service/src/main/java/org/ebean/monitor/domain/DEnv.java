package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Cache(nearCache = true, naturalKey = "name")
@Entity
@Table(name = "metric_env")
public class DEnv extends DBase {

  @Column(unique = true, nullable = false, length = 50)
  private final String name;

  public DEnv(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
