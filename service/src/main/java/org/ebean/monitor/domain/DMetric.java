package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Index;
import io.ebean.annotation.Length;

import javax.persistence.Column;
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
@Cache(nearCache = true, naturalKey = "key")
@Entity
@Table(name = "metric")
public class DMetric extends BaseDomain {

  /**
   * Derived as hash or concatenation of name + type.
   * Used as unique lookup as part of ingestion.
   */
  @Column(unique = true, nullable = false, length = 110)
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
   * The Application this metric belongs to if applicable.
   */
  @ManyToOne
  private DApp app;

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

  public DMetric(String key, String name, String type) {
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

  public void setApp(DApp app) {
    this.app = app;
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
