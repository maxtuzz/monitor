package org.ebean.monitor.domain;

import io.ebean.annotation.DbPartition;
import io.ebean.annotation.PartitionMode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Metric entry.
 */
@DbPartition(mode = PartitionMode.DAY, property = "eventTime")
@Entity
@Table(name = "metric_entry")
public class DMetricEntry extends BaseMetricEntry {

  public DMetricEntry(DMetric metric, DEnv env, DApp app, Instant eventTime, DDatabase db) {
    super(metric, env, app, eventTime, db);
  }
}
