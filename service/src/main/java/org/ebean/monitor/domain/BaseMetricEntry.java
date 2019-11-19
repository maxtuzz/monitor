package org.ebean.monitor.domain;

import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * Common Metric entry.
 */
@MappedSuperclass
public class BaseMetricEntry {

  @Id
  private long id;

  @DbForeignKey(noConstraint = true)
  @ManyToOne(optional = false)
  private final DMetric metric;

  @DbForeignKey(noConstraint = true)
  @ManyToOne(optional = false)
  private final DEnv env;

  @DbForeignKey(noConstraint = true)
  @ManyToOne(optional = false)
  private final DApp app;

  @DbForeignKey(noConstraint = true)
  @ManyToOne
  private final DDatabase db;

  /**
   * Time of metric collection truncated to the minute.
   */
  @NotNull
  private final Instant eventTime; // truncated to minute

  private Long count;
  private Long mean;
  private Long max;
  private Long total;

  BaseMetricEntry(DMetric metric, DEnv env, DApp app, Instant eventTime, DDatabase db) {
    this.metric = metric;
    this.env = env;
    this.app = app;
    this.eventTime = eventTime;
    this.db = db;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public DMetric getMetric() {
    return metric;
  }

  public DEnv getEnv() {
    return env;
  }

  public DApp getApp() {
    return app;
  }

  public DDatabase getDb() {
    return db;
  }

  public Instant getEventTime() {
    return eventTime;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getMean() {
    return mean;
  }

  public void setMean(Long mean) {
    this.mean = mean;
  }

  public Long getMax() {
    return max;
  }

  public void setMax(Long max) {
    this.max = max;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }
}
