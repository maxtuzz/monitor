package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricData;

class MetricKey {

  /**
   * Generate and return the unique key for a metric.
   */
  static String of(MetricData metric) {
    if (metric.hash != null && !metric.hash.isEmpty()) {
      // orm queries supply unique hash
      return metric.hash;
    } else {
      return metric.name + "|" + metric.type;
    }
  }

}
