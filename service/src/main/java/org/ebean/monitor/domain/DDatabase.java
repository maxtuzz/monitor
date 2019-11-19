package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Cache(nearCache = true, naturalKey = {"app", "name"})
@Entity
@Table(name = "metric_app_db")
@UniqueConstraint(columnNames = {"app_id", "name"})
public class DDatabase extends DBase {

  @ManyToOne @NotNull
  private final DApp app;

  @Length(40) @NotNull
  private final String name;

  public DDatabase(DApp app, String name) {
    this.app = app;
    this.name = name;
  }

  public DApp getApp() {
    return app;
  }

  public String getName() {
    return name;
  }
}
