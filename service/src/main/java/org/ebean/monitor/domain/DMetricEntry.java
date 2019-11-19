package org.ebean.monitor.domain;

import io.ebean.annotation.DbPartition;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.PartitionMode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@DbPartition(mode = PartitionMode.DAY, property = "eventTime")
@Entity
@Table(name = "metric_entry")
public class DMetricEntry {

  @Id
  private long id;

  @ManyToOne(optional = false)
  private final DMetric metric;

  @ManyToOne(optional = false)
  private final DEnv env;

  @ManyToOne(optional = false)
  private final DApp app;

  @ManyToOne(optional = false)
  private final DDatabase db;

  @NotNull
  private final Instant eventTime; // truncated to secs = 0
//  @NotNull
//  private final Instant eventTimeActual; // collection time in millis

  private Long count;
  private Long mean;
  private Long max;
  private Long total;

  public DMetricEntry(DMetric metric, DEnv env, DApp app, Instant eventTime, DDatabase db) {
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
