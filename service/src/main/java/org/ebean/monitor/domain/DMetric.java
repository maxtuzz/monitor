package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Index;
import io.ebean.annotation.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Cache(nearCache = true, naturalKey = "key")
@Entity
@Table(name="metric")
public class DMetric extends DBase {

  @Column(unique = true, nullable = false, length = 400)
  private final String key;

  @Index
  @Length(100)
  private final String name;

  @Length(10)
  private final String type;

  @Length(100)
  private String hash;

  @Length(150)
  private String loc;

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
