package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricData;
import org.ebean.monitor.domain.DMetric;

class IngestEntry {

  private final String key;
  private final MetricData data;
  private DMetric metric;

  IngestEntry(String key, MetricData data) {
    this.key = key;
    this.data = data;
  }

  IngestEntry assignMetric(DMetric metric) {
    this.metric = metric;
    return this;
  }

  public String getKey() {
    return key;
  }

  public MetricData getData() {
    return data;
  }


  DMetric getMetric() {
    return metric;
  }
}

