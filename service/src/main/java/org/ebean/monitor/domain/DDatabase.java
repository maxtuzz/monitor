package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The "Database" of the Application the metrics relate to.
 * <p>
 * Most Applications will have 1 database (eg. "db")
 * </p>
 */
@Cache(nearCache = true, naturalKey = {"app", "name"})
@Entity
@Table(name = "metric_app_db")
@UniqueConstraint(columnNames = {"app_id", "name"})
public class DDatabase extends DBase {

  /**
   * The Application this database belongs to.
   */
  @ManyToOne @NotNull
  private final DApp app;

  /**
   * The database name (eg. "db").
   */
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
