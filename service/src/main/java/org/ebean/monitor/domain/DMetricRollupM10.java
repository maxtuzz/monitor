package org.ebean.monitor.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Metric entries 10 minute rollup.
 */
//@DbPartition(mode = PartitionMode.WEEK, property = "eventTime")
@Entity
@Table(name = "metric_rollup_m10")
public class DMetricRollupM10 extends BaseMetricEntry {

  public DMetricRollupM10(DMetric metric, DEnv env, DApp app, Instant eventTime, DDatabase db) {
    super(metric, env, app, eventTime, db);
  }
}
