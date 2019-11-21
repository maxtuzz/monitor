package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Index;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The "Metric" which can be common across Applications.
 * <p>
 * The unique key for metric is a combination of name + type + hash + loc.
 * </p>
 */
@Cache(nearCache = true, naturalKey = {"app", "key"})
@Entity
@Table(name = "app_metric")
public class DMetric extends BaseDomain {

  /**
   * The Application this metric belongs to.
   */
  @ManyToOne
  @NotNull
  private final DApp app;

  /**
   * Derived as hash or concatenation of name + type.
   * Used as unique lookup as part of ingestion.
   */
  @Index
  @NotNull
  @Length(40)
  private final String key;

  /**
   * The metric name.
   */
  @Index
  @Length(100)
  private final String name;

  /**
   * The metric type (like TXN, L2N, IUD etc).
   */
  @Length(10)
  private final String type;

  /**
   * The code location if supplied. Expected to be class and line of code.
   */
  @Length(150)
  private String loc;

  /**
   * The SQL of the metric if supplied.
   */
  @Lob
  private String sql;

  public DMetric(DApp app, String key, String name, String type) {
    this.app = app;
    this.key = key;
    this.name = name;
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public DApp getApp() {
    return app;
  }

  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }
}
