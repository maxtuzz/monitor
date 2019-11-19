package org.ebean.monitor.api;

/**
 * An individual metric.
 */
public class MetricData {

  public String name;
  public String type;
  public String hash;
  public String loc;
  public String sql;

  public Long count;
  public Long mean;
  public Long max;
  public Long total;
}
