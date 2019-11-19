package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Index;
import io.ebean.annotation.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The "Metric" which can be common across Applications.
 * <p>
 * The unique key for metric is a combination of name + type + hash + loc.
 * </p>
 */
@Cache(nearCache = true, naturalKey = "key")
@Entity
@Table(name="metric")
public class DMetric extends DBase {

  /**
   * Derived concatenation of name + type + hash + loc
   */
  @Column(unique = true, nullable = false, length = 400)
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
   * Optional hash of the sql.
   */
  @Length(100)
  private String hash;

  /**
   * The location of the metric expected to be class and line of code.
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

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
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
