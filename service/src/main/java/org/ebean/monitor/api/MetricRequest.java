package org.ebean.monitor.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Metrics to ingest.
 */
public class MetricRequest {

  /**
   * The metric collection time.
   */
  public long eventTime;

  /**
   * The name of the application the metrics are collected for.
   */
  public String appName;

  /**
   * The name of the environment the metrics are collected for.
   */
  public String environment;

  /**
   * An Id for the server instance (like podId).
   */
  public String instanceId;

  /**
   * The database metrics.
   */
  public List<MetricDbData> dbs = new ArrayList<>();
}
