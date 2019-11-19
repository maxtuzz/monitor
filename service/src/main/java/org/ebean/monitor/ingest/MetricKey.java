package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricData;

class MetricKey {

  /**
   * Generate and return the unique key for a metric.
   */
  static String of(MetricData metric) {

    StringBuilder sb = new StringBuilder(200);
    append(sb, metric.name);
    append(sb, metric.type);
    append(sb, metric.hash);
    append(sb, metric.loc);
    return sb.toString();
  }

  private static void append(StringBuilder sb, String val) {
    sb.append(val == null ? "-" : val).append("|");
  }


}
